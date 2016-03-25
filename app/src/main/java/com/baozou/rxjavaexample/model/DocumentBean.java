package com.baozou.rxjavaexample.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangbaoliang on 2016/3/15.
 */
public class DocumentBean implements Parcelable{

    public static final int DISPLAY_TYPE_NORMAL=1;
    public static final int DISPLAY_TYPE_RECOMMEND=2;
    public static final int DISPLAY_TYPE_VIDEO=3;

    private long document_id;
    private int display_type = DISPLAY_TYPE_NORMAL;
    private String title;
    private String image;
    private String thumbnail;
    private String author_avatar;
    private String author_name;
    private String share_image;
    private int section_id;
    private String ga_prefix;
    private int vote_count;
    private String share_url;
    private String url;
    private int hit_count;
    private String hit_count_string;
    private boolean commented;
    private boolean voted;
    private boolean favorited;
    private String tag;
    private String tag_text;
    private String guide;
    private String guide_image;
    private int timestamp;
    private String author_summary;
    private String section_name;
    private String section_image;
    private String section_color;
    private boolean visiable;
    private String source_name;
    private List<Recommender> recommenders = new ArrayList<>();

    public DocumentBean(){
    }

    public DocumentBean(long id){
        this.document_id = id;
    }

    protected DocumentBean(Parcel in) {
        document_id = in.readLong();
        display_type = in.readInt();
        title = in.readString();
        image = in.readString();
        thumbnail = in.readString();
        author_avatar = in.readString();
        author_name = in.readString();
        share_image = in.readString();
        section_id = in.readInt();
        ga_prefix = in.readString();
        vote_count = in.readInt();
        share_url = in.readString();
        url = in.readString();
        hit_count = in.readInt();
        hit_count_string = in.readString();
        commented = in.readByte() != 0;
        voted = in.readByte() != 0;
        favorited = in.readByte() != 0;
        tag = in.readString();
        tag_text = in.readString();
        guide = in.readString();
        guide_image = in.readString();
        timestamp = in.readInt();
        author_summary = in.readString();
        section_name = in.readString();
        section_image = in.readString();
        section_color = in.readString();
        visiable = in.readByte() != 0;
        play_time = in.readInt();
        play_count = in.readInt();
        comment_count = in.readInt();
        play_count_string = in.readString();
        file_url = in.readString();
    }

    public static final Creator<DocumentBean> CREATOR = new Creator<DocumentBean>() {
        @Override
        public DocumentBean createFromParcel(Parcel in) {
            return new DocumentBean(in);
        }

        @Override
        public DocumentBean[] newArray(int size) {
            return new DocumentBean[size];
        }
    };

    public void setDocumentId(long documentId) {
        this.document_id = documentId;
    }
    public long getDocumentId() {
        return document_id;
    }

    public void setDisplayType(int displayType) {
        this.display_type = displayType;
    }
    public int getDisplayType() {
        return display_type;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public String getImage() {
        return image;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
    public String getThumbnail() {
        return thumbnail;
    }

    public void setAuthorAvatar(String authorAvatar) {
        this.author_avatar = authorAvatar;
    }
    public String getAuthorAvatar() {
        return author_avatar;
    }

    public void setAuthorName(String authorName) {
        this.author_name = authorName;
    }
    public String getAuthorName() {
        return author_name;
    }

    public void setShareImage(String shareImage) {
        this.share_image = shareImage;
    }
    public String getShareImage() {
        return share_image;
    }

    public void setSectionId(int sectionId) {
        this.section_id = sectionId;
    }
    public int getSectionId() {
        return section_id;
    }

    public void setGaPrefix(String gaPrefix) {
        this.ga_prefix = gaPrefix;
    }
    public String getGaPrefix() {
        return ga_prefix;
    }

    public void setVoteCount(int voteCount) {
        this.vote_count = voteCount;
    }
    public int getVoteCount() {
        return vote_count;
    }

    public void setShareUrl(String shareUrl) {
        this.share_url = shareUrl;
    }
    public String getShareUrl() {
        return share_url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }

    public void setHitCount(int hitCount) {
        this.hit_count = hitCount;
    }
    public int getHitCount() {
        return hit_count;
    }

    public void setHitCountString(String hitCountString) {
        this.hit_count_string = hitCountString;
    }
    public String getHitCountString() {
        return hit_count_string;
    }

    public void setCommented(boolean commented) {
        this.commented = commented;
    }
    public boolean getCommented() {
        return commented;
    }

    public void setVoted(boolean voted) {
        this.voted = voted;
    }
    public boolean getVoted() {
        return voted;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }
    public boolean getFavorited() {
        return favorited;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
    public String getTag() {
        return tag;
    }

    public void setTagText(String tagText) {
        this.tag_text = tagText;
    }
    public String getTagText() {
        return tag_text;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }
    public String getGuide() {
        return guide;
    }

    public void setGuideImage(String guideImage) {
        this.guide_image = guideImage;
    }
    public String getGuideImage() {
        return guide_image;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
    public int getTimestamp() {
        return timestamp;
    }

    public void setAuthorSummary(String authorSummary) {
        this.author_summary = authorSummary;
    }
    public String getAuthorSummary() {
        return author_summary;
    }

    public void setSectionName(String sectionName) {
        this.section_name = sectionName;
    }
    public String getSectionName() {
        return section_name;
    }

    public void setSectionImage(String sectionImage) {
        this.section_image = sectionImage;
    }
    public String getSectionImage() {
        return section_image;
    }

    public void setSectionColor(String sectionColor) {
        this.section_color = sectionColor;
    }
    public String getSectionColor() {
        return section_color;
    }

    public void setVisiable(boolean visiable) {
        this.visiable = visiable;
    }
    public boolean getVisiable() {
        return visiable;
    }

    //视频属性
    private int play_time;
    private int play_count;
    private int comment_count;
    private String play_count_string;
    private String file_url;

    public int getPlay_time() {
        return play_time;
    }

    public void setPlay_time(int play_time) {
        this.play_time = play_time;
    }

    public int getPlay_count() {
        return play_count;
    }

    public void setPlay_count(int play_count) {
        this.play_count = play_count;
    }

    public String getPlay_count_string() {
        return play_count_string;
    }

    public void setPlay_count_string(String play_count_string) {
        this.play_count_string = play_count_string;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public String getSource_name() {
        return source_name;
    }

    public void setSource_name(String source_name) {
        this.source_name = source_name;
    }

    public List<Recommender> getRecommenders() {
        return recommenders;
    }

    public void setRecommenders(List<Recommender> recommenders) {
        this.recommenders = recommenders;
    }

    public class Recommender {

        private long id = -1;
        private String name = "";
        private String avatar = "";

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(document_id);
        dest.writeInt(display_type);
        dest.writeString(title);
        dest.writeString(image);
        dest.writeString(thumbnail);
        dest.writeString(author_avatar);
        dest.writeString(author_name);
        dest.writeString(share_image);
        dest.writeInt(section_id);
        dest.writeString(ga_prefix);
        dest.writeInt(vote_count);
        dest.writeString(share_url);
        dest.writeString(url);
        dest.writeInt(hit_count);
        dest.writeString(hit_count_string);
        dest.writeByte((byte) (commented ? 1 : 0));
        dest.writeByte((byte) (voted ? 1 : 0));
        dest.writeByte((byte) (favorited ? 1 : 0));
        dest.writeString(tag);
        dest.writeString(tag_text);
        dest.writeString(guide);
        dest.writeString(guide_image);
        dest.writeInt(timestamp);
        dest.writeString(author_summary);
        dest.writeString(section_name);
        dest.writeString(section_image);
        dest.writeString(section_color);
        dest.writeByte((byte) (visiable ? 1 : 0));
        dest.writeInt(play_time);
        dest.writeInt(play_count);
        dest.writeInt(comment_count);
        dest.writeString(play_count_string);
        dest.writeString(file_url);
    }

    @Override
    public boolean equals(Object o) {
        return ((DocumentBean)o).getDocumentId()==document_id;
    }
}
