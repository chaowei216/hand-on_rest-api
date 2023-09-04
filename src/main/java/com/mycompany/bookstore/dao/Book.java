/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.dao;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
//interface: ko cần viết code/implement abstract method
//báo hiệu cho JVM biết phải làm gì - Marker interface
//interface: chỉ có duy nhất 1 hàm/functional interface
//Comparator, Comparable -> chơi với Lambda
//Dependency LOMBOK: get set giống C#
public class Book implements Serializable {
    private String isbn;
    private String title;
    private String author;
    private int edition;
    private int publishedYear;

    public Book() {
    }

    public Book(String isbn, String title, String author, int edition, int publishedYear) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.edition = edition;
        this.publishedYear = publishedYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    @Override
    public String toString() {
        return "Book{" + "isbn=" + isbn + ", title=" + title + ", author=" + author + ", edition=" + edition + ", publishedYear=" + publishedYear + '}';
    }
}
