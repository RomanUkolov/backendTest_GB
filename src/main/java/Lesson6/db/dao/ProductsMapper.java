package Lesson6.db.dao;

import Lesson6.db.model.Products;
import Lesson6.db.model.ProductsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table products
     *
     * @mbg.generated Wed Aug 24 10:08:14 MSK 2022
     */
    long countByExample(ProductsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table products
     *
     * @mbg.generated Wed Aug 24 10:08:14 MSK 2022
     */
    int deleteByExample(ProductsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table products
     *
     * @mbg.generated Wed Aug 24 10:08:14 MSK 2022
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table products
     *
     * @mbg.generated Wed Aug 24 10:08:14 MSK 2022
     */
    int insert(Products record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table products
     *
     * @mbg.generated Wed Aug 24 10:08:14 MSK 2022
     */
    int insertSelective(Products record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table products
     *
     * @mbg.generated Wed Aug 24 10:08:14 MSK 2022
     * @param example
     */
    List<Products> selectByExample(String example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table products
     *
     * @mbg.generated Wed Aug 24 10:08:14 MSK 2022
     */
    Products selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table products
     *
     * @mbg.generated Wed Aug 24 10:08:14 MSK 2022
     */
    int updateByExampleSelective(@Param("record") Products record, @Param("example") ProductsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table products
     *
     * @mbg.generated Wed Aug 24 10:08:14 MSK 2022
     */
    int updateByExample(@Param("record") Products record, @Param("example") ProductsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table products
     *
     * @mbg.generated Wed Aug 24 10:08:14 MSK 2022
     */
    int updateByPrimaryKeySelective(Products record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table products
     *
     * @mbg.generated Wed Aug 24 10:08:14 MSK 2022
     */
    int updateByPrimaryKey(Products record);
}