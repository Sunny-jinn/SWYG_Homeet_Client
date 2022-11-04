import { createProxyMiddleware } from "http-proxy-middleware";

module.exports = function (app: any) {
  app.use(
    "/test",
    createProxyMiddleware({
      target: "http://163.180.173.177",
      changeOrigin: true,
      ws: true,
    })
  );
};
