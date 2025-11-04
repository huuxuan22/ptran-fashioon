import { Navigate } from "react-router-dom";
import { useSelector } from "react-redux";

const ProtectedRoute = ({ children }) => {
    const token = localStorage.getItem("token");
    const userPrincipal = localStorage.getItem("user_principal");
    const { users } = useSelector((store) => store);

    const isAuthenticated = token || userPrincipal || users?.token || users?.user_principal;

    if (!isAuthenticated) {
        return <Navigate to="/login" replace />;
    }

    return children;
};

export default ProtectedRoute;

