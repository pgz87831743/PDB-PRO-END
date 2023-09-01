<script setup>
import {onMounted, ref} from 'vue'
import {ElMessageBox} from "element-plus"
import {Delete, Edit, Message} from "@element-plus/icons-vue"
import request from "@/utils/request"

        //商品表管理
        const sysShopApi={
        page(data){
        return request.post('/sysShop/page',data)
        },
        listAll(){
        return request.get('/sysShop/listAll')
        },
        getById(id){
        return request.get('/sysShop/getById/'+id)
        },
        add(data){
        return request.post('/sysShop/add',data)
        },
        deleteById(id){
        return request.delete('/sysShop/deleteById/'+id)
        },
        updateById(data){
        return request.put('/sysShop/updateById',data)
        }
        }



        const page = ref({
        pageSize: 10,
        pageNum: 1,
        search: '',
        total: 0
        })

        const tableData = ref([])
        const dialog = ref({
        dialogFormVisible: false,
        optionName: '新增',
        formDisabled: true,
        optionValue: null
        })

        const form = ref({})

        const search = () => {
        sysShopApi.page(page.value)
        .then(resp => {
        tableData.value = resp.data.data.records
        page.value.total = resp.data.data.total
        })
        }

        const handleAvatarSuccess = (response) => {
        form.value.img = response[0].url
        }

        const clickButton = (type, row) => {
        dialog.value.optionValue = type
        if (type === 'add') {
        dialog.value.dialogFormVisible = true
        dialog.value.optionName = '新增'
        dialog.value.formDisabled = false
        } else if (type === 'update') {
        sysShopApi.getById(row.id).then((resp) => {
        dialog.value.dialogFormVisible = true
        dialog.value.optionName = '修改'
        dialog.value.formDisabled = false
        form.value = resp.data.data
        })
        } else if (type === 'detail') {
        sysShopApi.getById(row.id).then((resp) => {
        dialog.value.dialogFormVisible = true
        dialog.value.optionName = '详情'
        dialog.value.formDisabled = true
        form.value = resp.data.data
        })
        } else if (type === 'delete') {
        ElMessageBox.confirm(
        '确定删除这条信息？',
        '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        }
        ).then(() => {
        sysShopApi.deleteById(row.id).then(() => {
        initTableData()
        })
        })
        }

        }

        const currentChange = (number) => {
        page.value.pageNum = number
        sysShopApi.page(page.value).then(resp => {
        tableData.value = resp.data.data.records
        page.value.total = resp.data.data.total
        })
        }

        const formSubmit = () => {
        dialog.value.dialogFormVisible = false
        if (dialog.value.optionValue === 'add') {
        sysShopApi.add(form.value)
        .then(() => {
        initTableData()
        })
        } else if (dialog.value.optionValue === 'update') {
        sysShopApi.updateById(form.value)
        .then(() => {
        initTableData()
        })
        }
        }

        const dialogClose = () => {
        form.value = {}
        }

        const initTableData = () => {
        sysShopApi.page(page.value)
        .then(resp => {
        tableData.value = resp.data.data.records
        page.value.total = resp.data.data.total
        })
        }

        onMounted(() => {
        initTableData()
        })


</script>

<template>
<div class="p-div">
<div style="background-color: white;height: 60px">
<el-row style="line-height: 40px;padding: 10px">
<el-col :span="1">
<el-button type="primary" @click="clickButton('add',null)">新增</el-button>
</el-col>
<el-col :span="5" :offset="1">
<el-input v-model="page.search" placeholder="请输入搜索内容" clearable @clear="initTableData"/>
</el-col>
<el-col :span="1" :offset="1">
<el-button type="success" @click="search">搜索</el-button>
</el-col>
</el-row>
</div>
<el-row>
<el-col>
<el-table :data="tableData" style="width: 100%" height="680px"
        :header-cell-style="{padding:'15px',fontWeight:'bold'}"
        :cell-style="{padding:'15px'}">
<el-table-column prop="id" label="主键"/>
<el-table-column prop="name" label="商品名称"/>
<el-table-column prop="description" label="商品说明"/>
<el-table-column prop="img" label="图片"/>
<el-table-column prop="price" label="价格"/>
<el-table-column prop="info" label="部门信息"/>
<el-table-column prop="createTime" label="创建时间"/>
<el-table-column prop="createBy" label="创建人"/>
<el-table-column label="操作" width="300px">
<template #default="scope">
<el-button type="primary" :icon="Edit" circle @click="clickButton('update', scope.row)"/>
<el-button type="info" :icon="Message" circle @click="clickButton('detail', scope.row)"/>
<el-button type="danger" :icon="Delete" circle @click="clickButton('delete',scope.row)"/>
</template>
</el-table-column>
</el-table>
</el-col>
</el-row>


<el-dialog v-model="dialog.dialogFormVisible" :title="dialog.optionName" @closed="dialogClose">
<el-form :model="form" label-position="right" label-width="150px" :disabled="dialog.formDisabled">
    <el-form-item label="商品名称">
    <el-input v-model="form.name" />
    </el-form-item>
    <el-form-item label="商品说明">
    <el-input v-model="form.description" />
    </el-form-item>
    <el-form-item label="图片">
    <el-input v-model="form.img" />
    </el-form-item>
    <el-form-item label="价格">
    <el-input v-model="form.price" />
    </el-form-item>
    <el-form-item label="部门信息">
    <el-input v-model="form.info" />
    </el-form-item>
</el-form>
<template #footer>
<span class="dialog-footer" v-if="!dialog.formDisabled">
<el-button @click="dialog.dialogFormVisible = false">取消</el-button>
<el-button type="success" @click="formSubmit">确认</el-button>
</span>
</template>
</el-dialog>


<!-- 分页 -->
<el-affix position="bottom" :offset="20">
<div class="paginationClass">
<el-pagination
        small
        background
        :total="page.total"
        :page-size="page.pageSize"
@current-change="currentChange"
        layout="total,prev, pager, next, jumper"
        />
</div>
</el-affix>
</div>


</template>


<style lang="scss" scoped>
        .el-row:nth-child(1) {
        margin-top: 0;
        }

        .el-row {
        margin-top: 30px;
        }

        .el-icon.avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 178px;
        height: 178px;
        text-align: center;
        }

        .paginationClass {
        position: fixed;
        bottom: 0;
        height: 60px;
        width: 100%;
        text-align: center;
        }


        .demo-image__error .image-slot {
        font-size: 30px;
        }

        .demo-image__error .image-slot .el-icon {
        font-size: 30px;
        }

        .demo-image__error .el-image {
        width: 100%;
        height: 200px;
        }

</style>