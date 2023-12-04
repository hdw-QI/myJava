layui.use('table', function () {
    const table = layui.table;

    // 第一个实例
    table.render({
        elem: '#test', // 指定table模板  也就是上方的table DOM
        // height: 500, // 指定Table模板的高度
        url: '/queryPageEmployeeList', // 对应的数据接口，这边直接是请求的json文件
        page: true, // 开启分页
        request:{
          pageName:'pageNo',
          limitName: 'pageSize'
        },
        totalRow:false, //关闭合计行。去除数据表格底下无论如何都会有一行灰色的空白行
        cols: [
            [
                //表头》》》指定的对应字段
                { field: 'id', title: 'ID', sort: true, fixed: 'left' },
                { field: 'name', title: '姓名' },
                { field: 'isMale', title: '性别', sort: true },
                { field: 'location', title: '住址' },
                { field: 'joinDate', title: '入职日期' },
                { field: 'salary', title: '薪资', sort: true },
                { field: 'deptId', title: '部门ID', sort: true },
                { field: 'photo', title: '头像' },
                {
                    title: '操作',
                    sort: true,
                    templet:function (d) {
                        return `<a class="layui-btn layui-btn-xs" lay-event="edit" data-id="${d.id}">编辑</a>
                                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" data-id="${d.id}">删除</a>`
                    }
                }
            ],
        ],
    })
// 点击表格头部，内部一些 dom上绑定了lay-event=""属性的一些节点所操作的事件
// tool中的test其实就是表格table节点的 lay-filter="test"这个属性值
    table.on('tool(test)',function(obj){
        // 在里面我们可以根据lay-event的值去进行相应的判断
        let layEvent=obj.event
        // 数据的id
        console.log(obj.data.id)
        let id=obj.data.id
        if (layEvent === 'del') {
            //删除
            layer.confirm('真的删除行么', function (index) {
                obj.del() //删除对应行（tr）的DOM结构，并更新缓存
                layer.close(index)
                //向服务端发送删除指令
                //发送请求删除数据库中的数据

                // 1、同步
                // window.location.href=`/uf/operationEmployee/delete?id=${id}`
                // 2、异步
                $.get(`/uf/operationEmployee/delete?id=${id}`,(res)=>{
                    if (res==='true'){
                        layer.alert('删除成功')
                    }else {
                        layer.alert('删除失败')
                    }
                })
            })
        } else if (layEvent === 'edit') {
            // 编辑
            // 数据回显
            $('#username').val()
            // ...等等
        }
    })
})