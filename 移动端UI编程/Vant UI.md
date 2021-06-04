# Vant UI

## 安装

```bash
# Vue 3项目，安装 Vant 3：
npm i vant@next -S
```

---



## 组件引入

> .babelrc是什么文件？

从linux的开发习惯作为历史，rc结尾的文件，一般都是运行时自动加载的文件、配置文件等，所以，**.babelrc文件是用来设置转码的规则和插件的。**

```bash
#  babelrc转码文件，es6还未被全部浏览器支持，babel将es6转化为浏览器能够识别的代码
# babel-plugin-import 是一款 babel 插件，它会在编译过程中将 import 的写法自动转换为按需引入的方式。
# 安装插件
npm i babel-plugin-import -D
```

```bash
// 在.babelrc 中添加配置(Vue 2.0)
// 注意：webpack 1 无需设置 libraryDirectory
{
  "plugins": [
    ["import", {
      "libraryName": "vant",
      "libraryDirectory": "es",
      "style": true
    }]
  ]
}
// 对于使用 babel7(Vue 3.0) 的用户，可以在 babel.config.js 中配置
module.exports = {
  plugins: [
    ['import', {
      libraryName: 'vant',
      libraryDirectory: 'es',
      style: true
    }, 'vant']
  ]
};
```



