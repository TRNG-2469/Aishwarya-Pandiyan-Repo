package com.cohort.library;

public class EBook extends Book implements Borrowable {
    private double fileSizeMB;

    public EBook(String title, String author, double fileSizeMB) {
        super(title, author);
        this.fileSizeMB = fileSizeMB;
    }

    public double getFileSizeMB() {
        return fileSizeMB;
    }

    public void setFileSizeMB(double fileSizeMB) {
        this.fileSizeMB = fileSizeMB;
    }

    public void downloadBook(double sizeMB) {
        if (sizeMB <= 0) {
            System.out.println("Error: file size must be positive. Download aborted.");
            return;
        }
        System.out.println("Downloading " + getTitle() + " (" + sizeMB + " MB)...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Download interrupted.");
            return;
        }
        System.out.println("Download complete.");
    }

    @Override
    public void borrowItem() {
        if (isBorrowed) {
            System.out.println(getTitle() + " is already borrowed.");
            return;
        }
        downloadBook(fileSizeMB);
        isBorrowed = true;
        System.out.println("Status change: " + getTitle() + " is now BORROWED.");
    }

    @Override
    public void returnItem() {
        if (!isBorrowed) {
            System.out.println(getTitle() + " is already available.");
            return;
        }
        isBorrowed = false;
        System.out.println("Status change: " + getTitle() + " is now AVAILABLE.");
    }

    @Override
    public String toString() {
        return super.toString() + " | File Size: " + fileSizeMB + " MB";
    }
}
