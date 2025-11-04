import baseAxios from "./BaseAxios"


export const login = async (data) => {
    try {
        debugger;
        // Login không cần token, nên không truyền Authorization header
        const res = await baseAxios.post(`/api/login`, data, {
            headers: {
                Authorization: undefined, // Không gửi token cho login
            },
        });
        return { success: true, data: res.data };
    } catch (error) {
        debugger;
        if (error.response) {
            return { success: false, data: error.response.data };
        } else {
            return { success: false, data: "Lỗi máy chủ, vui lòng thử lại!" };
        }
    }

}

export const register = async (data) => {
    try {
        const { confirmPassword, gender, ...userDTO } = data; // Loại bỏ confirmPassword
        // Chuyển đổi gender thành true nếu là male, ngược lại false
        const updatedGender = gender === 'male' ? true : false;
        // Cập nhật lại giá trị gender trong userDTO
        const updatedUserDTO = { ...userDTO, gender: updatedGender };
        const res = await baseAxios.put(`/api/register`, updatedUserDTO, {
            headers: {
                'Content-Type': 'application/json', // Đảm bảo là gửi dưới dạng JSON
            },
        });

        return { success: true, data: res.data };
    } catch (error) {
        if (error.response) {
            return { success: false, data: error.response.data };
        } else {
            return { success: false, data: "Lỗi máy chủ, vui lòng thử lại!" };
        }
    }
};

export const saveAfterCheck = async (userDTO, code) => {
    try {
        const res = await baseAxios.post(`/api/save`,
            userDTO, // Đây là @RequestBody
            {
                params: { code } // Đây là @RequestParam
            }
        );
        return { success: true, data: res.data };
    } catch (error) {
        if (error.response) {
            return { success: false, data: error.response.data };
        } else {
            return { success: false, data: "Lỗi máy chủ, vui lòng thử lại!" };
        }
    }
}

export const sendCodeAgain = async (email) => {
    try {
        const res = await baseAxios.post(`/api/send-again?email=${email}`,);
        return { success: true, data: res.data };
    } catch (error) {
        if (error.response) {
            return { success: false, data: error.response.data };
        } else {
            return { success: false, data: "Lỗi máy chủ, vui lòng thử lại!" };
        }
    }
}


/**
 * Lấy được mã giảm giá khi thanh toán 

 */
export const logOut = async (token) => {
    try {

        const res = await baseAxios.post(
            `/api/logout`,
            {
                headers: {
                    Authorization: `Bearer ${token}`,
                    "Content-Type": "application/json",
                },
            }
        );
        return { success: true, data: res.data };
    } catch (error) {
        if (error.response) {
            console.log(error.response.data);
            return { success: false, data: error.response.data };
        } else {
            return { success: false, data: "Lỗi máy chủ, vui lòng thử lại!" };
        }
    }
};