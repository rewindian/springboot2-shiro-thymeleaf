package com.sst.generator;

/**
 * @Author: Ian
 * @Date: 2019/4/4
 */
public class CodeGenerator {

    public static void main(String[] args) {
        GeneratorServiceEntity generatorServiceEntity = new GeneratorServiceEntity();
        //需要生成代码的表名
        String[] tables = {"sys_user"};
        generatorServiceEntity.generateCode(tables);
    }
}
