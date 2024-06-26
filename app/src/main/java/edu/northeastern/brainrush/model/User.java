package edu.northeastern.brainrush.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User implements Parcelable {
    private final String defaultUrl = "gs://brain-rush-db21a.appspot.com/profilePictures/default_user.jpeg";
    private String id;
    private String name;
    private String picture;
    private int experience;
    private int score;
    private Date date_created;
    private int no_of_likes;
    private int no_of_dislikes;
    private List<String> questions_created;
    private List<String> questions_answered;
    private List<String> daily_question_answered;

    //private time last time enter panel
    //private list of question paneled

    private List<String> question_reviewed;

    public User() {

    }

    public User(String name) {
        this.name = name;
        this.picture = defaultUrl;
        this.experience = 0;
        this.score = 0;
        this.date_created = new Date();
        this.no_of_dislikes = 0;
        this.no_of_likes = 0;
        this.questions_answered = new ArrayList<>();
        this.questions_created = new ArrayList<>();
        this.daily_question_answered = new ArrayList<>();
        this.question_reviewed = new ArrayList<>();
    }

    public User(String name, String id) {
        this.id = id;
        this.name = name;
        this.picture = defaultUrl;
        this.experience = 0;
        this.score = 0;
        this.date_created = new Date();
        this.no_of_dislikes = 0;
        this.no_of_likes = 0;
        this.questions_answered = new ArrayList<>();
        this.questions_created = new ArrayList<>();
        this.daily_question_answered = new ArrayList<>();
    }

    public User(String name, String picture, int experience, int score, Date date_created, int no_of_likes, int no_of_dislikes, List<String> questions_created, List<String> questions_answered, List<String> daily_question_answered) {
        this.name = name;
        this.picture = picture;
        this.experience = experience;
        this.score = score;
        this.date_created = date_created;
        this.no_of_likes = no_of_likes;
        this.no_of_dislikes = no_of_dislikes;
        this.questions_created = questions_created;
        this.questions_answered = questions_answered;
        this.daily_question_answered = daily_question_answered;
    }

    public List<String> getQuestion_reviewed() {
        return question_reviewed;
    }

    public void addExperience(int experiences){
        this.experience += experiences;
    }

    public int getLevel(){
        return this.experience/100 + 1;
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }

    public int getExperience() {
        return experience;
    }

    public int getScore() {
        return score;
    }

    public Date getDate_created() {
        return date_created;
    }

    public int getNo_of_likes() {
        return no_of_likes;
    }

    public int getNo_of_dislikes() {
        return no_of_dislikes;
    }

    public List<String> getQuestions_created() {
        return questions_created;
    }

    public List<String> getQuestions_answered() {
        return questions_answered;
    }

    public List<String> getDaily_question_answered() {
        return daily_question_answered;
    }

    public void add_questions_created(String id){
        Set<String> set = new HashSet<>(questions_created);
        if(!set.contains(id)){
            this.questions_created.add(id);
        }
    }

    public void add_question_reviewed(String id) {
        Set<String> set = new HashSet<>(this.question_reviewed);
        if(!set.contains(id)){
            this.questions_created.add(id);
        }
    }

    public void add_questions_answered(String id){
        Set<String> set = new HashSet<>(questions_answered);
        if(!set.contains(id)){
            this.questions_answered.add(id);
        }
    }

    public void add_daily_question_answered(String id){
        Set<String> set = new HashSet<>(daily_question_answered);
        if(!set.contains(id)){
            this.daily_question_answered.add(id);
        }
    }

    public int increment_likes(){
        this.no_of_likes++;
        return this.no_of_likes;
    }

    public int increment_dislikes(){
        this.no_of_dislikes++;
        return this.no_of_dislikes;
    }

    // Parcelable implementation
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(picture != null ? picture : defaultUrl);
        dest.writeInt(experience);
        dest.writeInt(score);
        dest.writeLong(date_created != null ? date_created.getTime() : -1L);
        dest.writeInt(no_of_likes);
        dest.writeInt(no_of_dislikes);
        dest.writeList(questions_created);
        dest.writeList(questions_answered);
        dest.writeList(question_reviewed);
        dest.writeList(daily_question_answered);
    }

    protected User(Parcel in) {
        id = in.readString();
        name = in.readString();
        picture = in.readString();
        experience = in.readInt();
        score = in.readInt();
        long tmpDateCreated = in.readLong();
        date_created = tmpDateCreated == -1L ? null : new Date(tmpDateCreated);
        no_of_likes = in.readInt();
        no_of_dislikes = in.readInt();
        question_reviewed = new ArrayList<>();
        in.readList(question_reviewed, Long.class.getClassLoader());
        questions_created = new ArrayList<>();
        in.readList(questions_created, Long.class.getClassLoader());
        questions_answered = new ArrayList<>();
        in.readList(questions_answered, Long.class.getClassLoader());
        daily_question_answered = new ArrayList<>();
        in.readList(daily_question_answered, Long.class.getClassLoader());
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
