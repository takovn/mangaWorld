package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Manga {

    int id;
    String name;
    String imageUrl;
    String author;
    String owner;
    List<String> typeNames;
    String date;
    String status;
    int chap;
    int followNumber;

    public Manga() {
    }

    public Manga(int id, String name, String imageUrl, String author, List<String> typeNames, String date, String status) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.author = author;
        this.typeNames = typeNames;
        this.date = date;
        this.status = status;
    }

    public Manga(int id, String name, String imageUrl, String author, String owner, List<String> typeNames, String date, String status, int chap, int followNumber) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.author = author;
        this.owner = owner;
        this.typeNames = typeNames;
        this.date = date;
        this.status = status;
        this.chap = chap;
        this.followNumber = followNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<String> getTypeNames() {
        return typeNames;
    }

    public void setTypeNames(List<String> typeNames) {
        this.typeNames = typeNames;
    }

    public String getDate() {
        return date;
    }

    public String getFormattedDate() {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng đầu vào
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy"); // Định dạng đầu ra

        try {
            Date parsedDate = inputFormat.parse(this.date);
            return outputFormat.format(parsedDate);
        } catch (ParseException e) {
            System.out.println(e);
            return this.date; // Trả về chuỗi gốc nếu có lỗi
        }
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getChap() {
        return chap;
    }

    public void setChap(int chap) {
        this.chap = chap;
    }

    public int getFollowNumber() {
        return followNumber;
    }

    public void setFollowNumber(int followNumber) {
        this.followNumber = followNumber;
    }

}
