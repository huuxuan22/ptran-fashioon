import { Navigate } from "react-router-dom";
import { useSelector } from "react-redux";


const PublicRoute = ({ children }) => {
    const token = localStorage.getItem("token");
    const userPrincipal = localStorage.getItem("user_principal");
    const { users } = useSelector((store) => store);

    // Kiểm tra đã đăng nhập chưa
    const isAuthenticated = token || userPrincipal || users?.token || users?.user_principal;

    if (isAuthenticated) {
        // Đã đăng nhập, redirect về trang chủ
        return <Navigate to="/" replace />;
    }

    return children;
};

export default PublicRoute;

