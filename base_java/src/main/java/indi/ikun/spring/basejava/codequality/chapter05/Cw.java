package indi.ikun.spring.basejava.codequality.chapter05;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 75.集合中的元素必须做到compareTo和equals同步
 *
 * indexOf方法查找时，遍历每个元素，然后比较equals方法的返回值，equals是判断元素是否相等
 * binarySearch二分法查找时，依据的是每个元素的compareTo方法返回值，compareTo是判断元素在排序中的位置是否相同
 *
 * 一个是决定排序位置，一个是决定相等，那我们就应该保证当排序位置相同时，其equals也相同，否则就会产生逻辑混乱
 *
 */
public class Cw {
    public static void main(String[] args) {
        List<CityCw> cityCws=new ArrayList<>();
        cityCws.add(new CityCw("021","上海"));
        cityCws.add(new CityCw("021","沪"));

        Collections.sort(cityCws);

        CityCw cityCw=new CityCw("021","沪");
        int index=cityCws.indexOf(cityCw);//equals比较code
        System.err.println(index);
        int index1=Collections.binarySearch(cityCws,cityCw);//compareTo比较name
        System.err.println(index1);
    }

}
class CityCw implements Comparable<CityCw>{
    private String code;
    private String name;

    @Override
    public int compareTo(CityCw o) {
        //按照城市名称排序
        return new CompareToBuilder().append(name,o.name).toComparison();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityCw cityCw = (CityCw) o;
        //与compareTo保持一致
//        return new EqualsBuilder().append(code,cityCw.code).isEquals();
        return new EqualsBuilder().append(name,cityCw.name).isEquals();
    }

    public CityCw(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
