import baseAxios from "./BaseAxios";
import { da } from "date-fns/locale";



export const getAllFeedbacks = async (data) => {
    try {
        const res = await baseAxios.get(
            `/api/feedback/product?productId=${data.categoryId}&page=${data.page}&size=${data.size}&rating=${data.rating}`,
            {
                headers: {
                    Authorization: `Bearer ${data.token}`,
                    "Content-Type": "application/json",
                },
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
};

export const uploadImageFeedback = async (formData, unique, token) => {
    try {
        const res = await baseAxios.post(
            `/api/comment/upload-image-feedback?unique=${unique}`,
            formData, // body rỗng vì bạn chưa truyền dữ liệu gì
            {
                headers: {
                    Authorization: `Bearer ${token}`,
                    "Content-Type": "multipart/form-data", // Quan trọng
                },
            }
        );
        console.log(res.data);

        return { success: true, data: res.data };
    } catch (error) {
        console.log(error);
        if (error.response) {
            return { success: false, data: error.response.data };
        } else {
            return { success: false, data: "Lỗi máy chủ, vui lòng thử lại!" };
        }

    }
};

export const uploadImageFeedbackResponse = async (formData, unique, token) => {
    try {
        console.log("ddax vao trong API");

        const res = await baseAxios.post(
            `/api/comment/upload-mess-media?unique=${unique}`,
            formData, // body rỗng vì bạn chưa truyền dữ liệu gì
            {
                headers: {
                    Authorization: `Bearer ${token}`,
                    "Content-Type": "multipart/form-data", // Quan trọng
                },
            }
        );
        console.log(res.data);

        return { success: true, data: res.data };
    } catch (error) {
        console.log(error);
        if (error.response) {
            return { success: false, data: error.response.data };
        } else {
            return { success: false, data: "Lỗi máy chủ, vui lòng thử lại!" };
        }

    }
};

export const loadAllFeedbackMess = async (feeckbackId, token) => {
    console.log("feedbackId", feeckbackId);

    try {
        const res = await baseAxios.post(
            `/api/feedback/feedback-message/${feeckbackId}`,
            {
                headers: {
                    Authorization: `Bearer ${token}`,
                    "Content-Type": "multipart/form-data", // Quan trọng
                },
            }
        );
        return { success: true, data: res.data };
    } catch (error) {
        console.log(error);
        if (error.response) {
            return { success: false, data: error.response.data };
        } else {
            return { success: false, data: "Lỗi máy chủ, vui lòng thử lại!" };
        }

    }
};