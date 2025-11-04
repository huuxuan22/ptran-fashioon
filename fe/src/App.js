import { BrowserRouter } from "react-router-dom";
import "swiper/css";
import "@fontsource/roboto";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js";
import "@fontsource/inter";
import { Box } from "@mui/material";
import AppRoutes from "./routes";

function App() {
  return (
    <BrowserRouter>
      <Box component="main" sx={{ flex: 1 }}>
        <AppRoutes />
      </Box>
    </BrowserRouter>
  );
}

export default App;
