package com.kyrie.order.controller;

import com.kyrie.base.model.PageParams;
import com.kyrie.base.model.PageResult;
import com.kyrie.base.model.RestResponse;
import com.kyrie.order.dto.QueryOrderParamsDto;
import com.kyrie.order.pojo.Order;
import com.kyrie.order.sercice.IOrderService;
import com.kyrie.order.sercice.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @auther: jijin
 * @date: 2023/7/2 0:09 周日
 * @project_name: MicroServiceTest
 * @version: 1.0
 * @description TODO
 */
@RestController
@Api(value = "订单接口 value", tags = "订单接口 tags")
public class IOrderController {

    @Autowired
    IOrderService iOrderService;

    @ApiOperation("按条件查询返回分页数据")
    @PostMapping("/listpage")
    public RestResponse listpage(PageParams pageParams,@RequestBody(required = false) QueryOrderParamsDto queryOrderParamsDto) {
        return new RestResponse(true,null,iOrderService.queryOrderList(pageParams, queryOrderParamsDto));
    }

    @ApiOperation("根据id查询单个订单")
    @GetMapping("/{id}")
    public RestResponse queryById(@PathVariable("id") Long id) {
        return new RestResponse(true,null,iOrderService.queryById(id));
    }

    @ApiOperation("增加订单")
    @PostMapping
    public RestResponse save(@RequestBody Order order) {
        return new RestResponse(iOrderService.save(order));
    }

    @ApiOperation("删除订单")
    @DeleteMapping("/{id}")
    public RestResponse delete(@PathVariable("id") Long id) {
        return new RestResponse(iOrderService.removeById(id));
    }

    @ApiOperation("修改订单")
    @PutMapping
    public RestResponse updata(@RequestBody Order order) {
        return new RestResponse(iOrderService.updateById(order));
    }
}
