import express from "express";
import colors from "colors";
import dotenv from "dotenv";
import morgan from "morgan";
import connectDB from "./config/db.js";
import authRoutes from "./routes/authRoute.js";
import cors from "cors";

// configure env
dotenv.config();

// const express = require("express");
// const colors = require("colors");
// The above comments are used when we do not add "type": "module" in package.json

// database config
connectDB();

// rest object
const app = express();

// middlewares
app.use(cors());
app.use(express.json());
app.use(morgan("dev"));

// routes
app.use("/api/v1/auth", authRoutes);

// rest api
app.get("/", (req, res) => {
  res.send(`<h1>Welcome to ecommerce app</h1>`);
});

// PORT
const PORT = process.env.PORT || 8080;

app.listen(PORT, () => {
  console.log(
    `Server running on ${process.env.DEV_MODE} on ${PORT}`.bgCyan.white
  );
});
