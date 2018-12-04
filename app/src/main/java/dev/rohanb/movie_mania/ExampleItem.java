package dev.rohanb.movie_mania;

public class ExampleItem {

    private String mPosterUrl;
    private String mTitle;
    private String mReleaseDate;
    private Boolean mAdult;
    private String mOverView;
    private Double mRatings;

    public ExampleItem(String posterUrl, String title, String releaseDate,Boolean adult,String overView, Double ratings)
    {
        mPosterUrl=posterUrl;
        mTitle=title;
        mReleaseDate=releaseDate;
       mAdult=adult;
       mOverView=overView;
       mRatings=ratings;
    }

    public String getmPosterUrl() {
        return mPosterUrl;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public Boolean getmAdult() {
        return mAdult;
    }

    public String getmOverView() {
        return mOverView;
    }

    public Double getmRatings() {
        return mRatings;
    }
}
