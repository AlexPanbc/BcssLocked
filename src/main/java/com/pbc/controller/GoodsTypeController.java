package com.pbc.controller;

import com.pbc.domainentity.qentity.goodsType.MGoodsType;
import com.pbc.domainentity.qentity.goodsType.UpdGoodsType;
import com.pbc.po.GoodsType;
import com.pbc.service.GoodsTypeService;
import com.pbc.utils.Tools.BaseController;
import com.pbc.utils.Tools.BeanUtilsExtends;
import com.pbc.utils.Tools.DateTools;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Alex on 2016/10/6.
 */
@Controller
@RequestMapping("/goodsType")
public class GoodsTypeController extends BaseController {
    private static Logger log = LogManager.getLogger(GoodsTypeController.class);

    @Autowired
    private GoodsTypeService goodsTypeService;
//// TODO: 2016/10/7 http://localhost:8080/BcssLocked/goodsType/add
//// TODO: 2016/10/7 {"name":"鼠类"}

    /**
     * 添加商品类型
     *
     * @param name
     * @param map
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8;")
    public String add(@RequestBody String name, ModelMap map) {
        if (name.isEmpty()) log.error("商品类型创建操作参数不能为空 name：" + name);
        GoodsType g = new GoodsType();
        g.setName(name);
        g.setCreatedon(new Date());
        g.setModifiedon(new Date());
        log.info("创建商品类型，接口参数：" + toJSONString(g));
        if (goodsTypeService.add(g) == 1)
            map.put("allGoodsType", goodsTypeService.getAll());
        return "AllGoodsType";
    }
//// TODO: 2016/10/7 http://localhost:8080/BcssLocked/goodsType/del/6

    /**
     * 删除商品类型
     *
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8;")
    public String del(@PathVariable("id") int id, ModelMap map) {
        log.info("删除商品类型，接口参数：" + toJSONString(id));
        if (goodsTypeService.del(id) == 1)
            map.put("allGoodsType", goodsTypeService.getAll());
        return "AllGoodsType";
    }
    //// TODO: 2016/10/7 http://localhost:8080/BcssLocked/goodsType/upd
    //// TODO: 2016/10/7 {"id":7,"name":"人类"}

    /**
     * 修改商品类型
     *
     * @param ag
     * @param map
     * @return
     */
    @RequestMapping(value = "/upd", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8;")
    public String upd(@Valid @RequestBody UpdGoodsType ag, ModelMap map, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> ap = getErrors(result);
            log.error(ap);
            return ERROR;
        }
        GoodsType gt = goodsTypeService.get(ag.getId());
        if (gt == null) log.error("商品类型不存在");
        //抛异常信息提示
        BeanUtils.copyProperties(gt, ag);
        gt.setModifiedon(new Date());
        log.info("修改商品类型，接口参数：" + toJSONString(gt));
        if (goodsTypeService.upd(gt) == 1)
            map.put("allGoodsType", goodsTypeService.getAll());
        return "AllGoodsType";
    }
    //// TODO: 2016/10/7 http://localhost:8080/BcssLocked/goodsType/get/1

    /**
     * 获取商品类型
     *
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8;")
    public String get(@PathVariable("id") int id, ModelMap map) {
        log.info("根据序号获取商品类型，接口参数：" + id);
        GoodsType goodsType = goodsTypeService.get(id);
        if (goodsType == null)
            log.error("数据不存在：" + id);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = sdf.format(goodsType.getCreatedon());
        String modifyTime = sdf.format(goodsType.getModifiedon());
        MGoodsType mg = new MGoodsType();
        BeanUtilsExtends.copyProperties(mg, goodsType);
        mg.setCreatedon(createTime);
        mg.setModifiedon(modifyTime);
        map.put("goodsType", mg);
        map.put("goodsTypejson", toJSONString(mg));
        return "GoodsType";
    }

//// TODO: 2016/10/7 http://localhost:8080/BcssLocked/goodsType/getAll 

    /**
     * 获取所有商品类型
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8;")
    public String getAll(ModelMap map) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        log.info("查询所有商品类型");
        List<MGoodsType> lstMgt = new ArrayList<MGoodsType>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (GoodsType t : goodsTypeService.getAll()) {
            String createTime = sdf.format(t.getCreatedon());
            String modifyTime = sdf.format(t.getModifiedon());
            MGoodsType mt = new MGoodsType();
            BeanUtilsExtends.copyProperties(mt, t);//重写BeanUtils.copyProperties
            mt.setCreatedon(createTime);
            mt.setModifiedon(modifyTime);
//            PropertyUtils.copyProperties(mt, t);  // TODO: 2016/10/9 属性类型匹配   略慢
            //   BeanUtils.copyProperties(t,mt);//// TODO: 2016/10/9 属性名称一致则赋值 不匹配类型 效率高
            lstMgt.add(mt);
        }


        map.put("allGoodsType", lstMgt);
        map.put("jsonGoodsType", toJSONString(lstMgt));
        return "AllGoodsType";
    }
}
