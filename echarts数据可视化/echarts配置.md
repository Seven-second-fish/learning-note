# echarts配置

## 两种方式使用echarts

- 通过 npm 获取 echarts，`npm install echarts --save`

  > 引入echarts

  ```js
  import * as echarts from 'echarts';
  
  // 基于准备好的dom，初始化echarts实例
  var myChart = echarts.init(document.getElementById('main'));
  // 绘制图表
  myChart.setOption({
      title: {
          text: 'ECharts 入门示例'
      },
      tooltip: {},
      xAxis: {
          data: ['衬衫', '羊毛衫', '雪纺衫', '裤子', '高跟鞋', '袜子']
      },
      yAxis: {},
      series: [{
          name: '销量',
          type: 'bar',
          data: [5, 20, 36, 10, 10, 20]
      }]
  });
  -------------
  //如果是 webpack 打包，也可以 require 引入
  require('echarts');
  require('echarts/extension/bmap/bmap');
  ```

- cdn引入使用

  ```js
  <script src="https://cdn.jsdelivr.net/npm/echarts@5.1.2/dist/echarts.min.js"></script>
  ```

  ---
  
  ```javascript
  <!--引入百度地图的jssdk，这里需要使用你在百度地图开发者平台申请的 ak-->
  <script src="http://api.map.baidu.com/api?v=2.0&ak="></script>
  <!-- 引入 ECharts -->
  <script src="dist/echarts.min.js"></script>
  <!-- 引入百度地图扩展 -->
  <script src="dist/extension/bmap.min.js"></script>
  ```
  
  > baidu地图使用
  
  扩展主要提供了跟 geo 一样的坐标系和底图的绘制，因此配置方式非常简单
  
  ```javascript
  option = {
      // 加载 bmap 组件
      bmap: {
          // 百度地图中心经纬度
          center: [120.13066322374, 30.240018034923],
          // 百度地图缩放
          zoom: 14,
          // 是否开启拖拽缩放，可以只设置 'scale' 或者 'move'
          roam: true,
          // 百度地图的自定义样式，见 http://developer.baidu.com/map/jsdevelop-11.htm
          mapStyle: {}
      },
      series: [{
          type: 'scatter',
          // 使用百度地图坐标系
          coordinateSystem: 'bmap',
          // 数据格式跟在 geo 坐标系上一样，每一项都是 [经度，纬度，数值大小，其它维度...]
          data: [ [120, 30, 1] ]
      }]
  }
  
  // 获取百度地图实例，使用百度地图自带的控件
  var bmap = chart.getModel().getComponent('bmap').getBMap();
  bmap.addControl(new BMap.MapTypeControl());
  ```
  
  

