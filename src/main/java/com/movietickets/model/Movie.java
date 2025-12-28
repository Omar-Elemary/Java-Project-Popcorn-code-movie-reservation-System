package com.movietickets.model;

public class Movie {
    private int movieId;
    private String title;
    private String description;
    private int duration;                            
    private double rating;
    private String category;
    public Movie() {
        
    }

    public Movie(int movieId, String title, String description, int duration, /*double rating,*/ String category) {
        this.movieId = movieId;
        this.title = title;
        this.description = description;
        this.duration = duration;
        //this.rating = rating;
        this.category = category;
    }
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public int getDuration() {
        return duration;
    }


    public void setDuration(int duration) {
        this.duration = duration;
    }


    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        if (rating >= 0 && rating <= 10) {
            this.rating = rating;
        }   
        else {
            System.out.println("Rating must be between 0 and 10");
        }
    }


    
    public String getCategory() {
        return category;
    }


    public void setCategory(String category) {
        this.category = category;
    }

    public void getDetails() {
        System.out.printf(
                "Movie ID: %d%nTitle: %s%nDescription: %s%nDuration: %d minutes%nRating: %.1f%nCategory: %s%n",
                movieId, title, description, duration, rating, category
        );
    }
         
    @Override
    public String toString() {
        return title + " (" + category + ") - Rating: " + rating;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Movie )) return false;
        Movie movie = (Movie) o;
        return movieId == movie.movieId;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(movieId);
    }

}

