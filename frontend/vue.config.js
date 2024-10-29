const { defineConfig } = require('@vue/cli-service');
const webpack = require('webpack');

module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    port: 3000
  },
  configureWebpack: {
    plugins: [
      new webpack.DefinePlugin({
        '__VUE_OPTIONS_API__': JSON.stringify(true), // Włączone wsparcie dla Options API
        '__VUE_PROD_DEVTOOLS__': JSON.stringify(false), // Wyłączenie devtools w produkcji
        '__VUE_PROD_HYDRATION_MISMATCH_DETAILS__': JSON.stringify(false) // Zdefiniowanie flagi
      })
    ]
  }
});
