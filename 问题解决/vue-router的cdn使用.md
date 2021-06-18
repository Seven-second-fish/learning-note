# vue-router的cdn使用

## 前端示例代码

```vue
<div id="app">
  <p>$route.name: {{$route.name}}</p>
  <p>$route.path: {{$route.path}}</p>
  <p>$route.query: {{$route.query}}</p>
</div>

<script src="/static/js/vue.js"></script>
<script src="/static/js/vue-router.js"></script>

<script>
  const router = new VueRouter({
    mode: "history", // 默认使用hash模式，url会出现#
  });

  const app = new Vue({
    router,
    el: "#app",
  });
</script>
```

---



## 显示结果

```vue
$route.name:

$route.path: /demo.html

$route.query: { "id": "44", "name": "Tom" }
```

确实顶用，百度一下cdn使用vue的不多，用这个直接上手。

