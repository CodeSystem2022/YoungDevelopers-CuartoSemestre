
import  express  from "express";
import morgan from "morgan";
import tareasRoutes from "./router/tareas.routes.js";
import authRoutes from "./router/auth.routes.js";

const app = express();
app.use(morgan("dev"));
app.use(express.json());
app.use(express.urlencoded({extended:false}));

app.get("/",(req,res)=>res.json({mesaage:"Bienven idos a mi proyecto"}));
app.use("/api",tareasRoutes);
app.use("/api",authRoutes);

app.use((req, res,next) => {
    res.status(500).json({
        status : "error",
        message : err.message
    });
});


export default app;
