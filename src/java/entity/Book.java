/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author sillamae kutsekool
 */

public class Book {

    private Long id;
    private String name;
    private String author;
    private Integer publishedYear;

    public Book() {
    }

    public Book( String name, String author, Integer publishedYear, int parseInt) {
        this.name = name;
        this.author = author;
        this.publishedYear = publishedYear;        
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }

    @Override
    public String toString() {
        return "Book{" 
                + "name=" + name 
                + ", author=" + author 
                + ", publishedYear=" + publishedYear 
                + '}';
        
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }      
}
