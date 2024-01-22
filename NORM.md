# 代码规范

* Service/DAO 层方法命名规则
    * 获取单个对象的方法用get做前缀。
    * 获取多个对象的方法用list做前缀，复数结尾，如：listObjects。
    * 获取统计值的方法用count做前缀。
    * 插入的方法用save/insert做前缀。
    * 删除的方法用remove/delete做前缀。
    * 修改的方法用update做前缀。

*
    * 新增接口：addXxx
    * 分页接口：pagingXxx
    * 列表接口：listingXxx\queryXxx
    * 树接口：plantXxx
    * 更新接口：updateXxx
    * 获取详情接口：detailsXxx\getXxx
    * 删除接口：deleteXxx
    * 激活接口：activateXxx
    * 冻结接口：freezeXxx
    * 下载文件：downloadFile
    * 判断文件存在：existsFile
    * 复制文件：copyFile
    * 上传文件：uploadFile
    * 文件压缩：compressFile
    * 文件解压：decompressFile
    * 同步文件：syncFile
    * 识别文件：recogniseFile
    * 获取文件详情：detailsFile

* git commit 提交规范
    * feat：新功能（feature）
    * fix：修补bug
    * docs：文档（documentation）
    * style： 格式（不影响代码运行的变动）
    * refactor：重构（即不是新增功能，也不是修改bug的代码变动）
    * test：增加测试
    * chore：构建过程或辅助工具的变动
