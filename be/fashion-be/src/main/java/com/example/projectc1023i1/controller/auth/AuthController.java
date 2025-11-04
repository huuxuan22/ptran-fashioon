package com.example.projectc1023i1.controller.auth;

import com.example.projectc1023i1.Dto.UserDTO;
import com.example.projectc1023i1.Exception.UserExepion;
import com.example.projectc1023i1.component.JwtTokenUtils;
import com.example.projectc1023i1.model.Roles;
import com.example.projectc1023i1.model.Users;
import com.example.projectc1023i1.request.LoginRequest;
import com.example.projectc1023i1.respone.LoginResponse;
import com.example.projectc1023i1.respone.errorsValidate.LoginErrors;
import com.example.projectc1023i1.respone.errorsValidate.RegisterErrors;
import com.example.projectc1023i1.service.AuthService;
import com.example.projectc1023i1.service.RedisService;
import com.example.projectc1023i1.mapper.ValidationErrorMapper;
import com.example.projectc1023i1.service.VerificationService;
import com.example.projectc1023i1.service.impl.IUserService;
import com.example.projectc1023i1.utils.GetTokenFromRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class AuthController {
    
    private final AuthService authService;
    private final ValidationErrorMapper validationErrorMapper;
    private final JwtTokenUtils jwtTokenUtils;
    private final RedisService redisService;
    private final IUserService userService;
    private final VerificationService verificationService;
    private final AuthenticationManager authenticationManager;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @Valid @RequestBody LoginRequest loginRequest,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            LoginErrors loginErrors = validationErrorMapper.mapToLoginErrors(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginErrors);
        }
        
        LoginResponse loginResponse = authService.authenticate(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }
    
    @PostMapping("/logout")
    public ResponseEntity<?> logout(
            @AuthenticationPrincipal Users user,
            HttpServletRequest request
    ) {
        String token = GetTokenFromRequest.getTokenFromRequest(request);
        if (token != null) {
            String username = jwtTokenUtils.extractUserName(token);
            redisService.addTokenList(username, token);
            return ResponseEntity.ok("Đăng xuất thành công");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Không tìm thấy token hợp lệ");
        }
    }
    
    @PutMapping("/register")
    public ResponseEntity<?> register(
            @Valid @RequestBody UserDTO userDTO,
            BindingResult bindingResult
    ) throws UserExepion {
        if (bindingResult.hasErrors()) {
            RegisterErrors registerErrors = validationErrorMapper.mapToRegisterErrors(bindingResult);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(registerErrors);
        }
        
        // Gửi mã xác thực và lưu UserDTO vào Redis
        verificationService.sendVerificationCode(userDTO);
        return ResponseEntity.ok("Mã xác thực đã được gửi đến email: " + userDTO.getEmail());
    }
    
    @PostMapping("/send-again")
    public ResponseEntity<?> sendCodeAgain(@RequestParam String email) {
        UserDTO userDTO = verificationService.getUserDTOFromRedis(email);
        
        if (userDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Không tìm thấy dữ liệu đăng ký. Vui lòng đăng ký lại.");
        }
        
        try {
            verificationService.sendVerificationCode(userDTO);
            return ResponseEntity.ok("Mã xác thực đã được gửi lại đến email: " + email);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi gửi lại mã xác thực: " + e.getMessage());
        }
    }
    
    @PostMapping("/save")
    public ResponseEntity<?> verifyCodeAndSave(
            @RequestParam String email,
            @RequestParam String code
    ) throws UserExepion {
        UserDTO userDTO = verificationService.verifyCode(email, code);
        
        if (userDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Mã xác thực không đúng hoặc đã hết hạn");
        }
        
        Users user = userService.convertUserDTOToUser(userDTO);
        user.setRole(Roles.builder().roleId(1).roleName(Roles.USER).build());
        userService.saveUser(user);
        
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword())
        );
        Users registeredUser = (Users) authentication.getPrincipal();
        String jwt = jwtTokenUtils.generateToken(registeredUser);
        
        return ResponseEntity.ok(jwt);
    }
}

