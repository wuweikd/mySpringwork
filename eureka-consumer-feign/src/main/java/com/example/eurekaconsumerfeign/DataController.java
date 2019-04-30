package com.example.eurekaconsumerfeign;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Api("保存接口")
@RestController
public class DataController {

    @Autowired
    PersonRepository personRepository;
    //测试保存数据
    @ApiOperation(value="/get/hello方法", notes="这是个notes")
    @ApiImplicitParam(name = "user", value = "hello方法", required = true, dataType = "User")
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public Person save(){
        Person  p = new Person("wyf",32);
        Collection<Location> locations =  new LinkedHashSet<Location>();
        Location loc1 = new Location("上海","2009");
        Location loc2 = new Location("合肥","2010");
        Location loc3 = new Location("广州","2011");
        Location loc4 = new Location("马鞍山","2012");
        locations.add(loc1);
        locations.add(loc2);
        locations.add(loc3);
        locations.add(loc4);
        p.setLocations(locations);

        return personRepository.save(p);
    }
    //测试方法名查询
    @RequestMapping(value = "/name", method = RequestMethod.GET)
    public Person q1(String name){
        return personRepository.findByName(name);
    }
    //测试@Query查询
    @RequestMapping(value = "/age", method = RequestMethod.GET)
    public List<Person> q2(Integer age){
        return personRepository.withQueryFindByAge(age);
    }

}
