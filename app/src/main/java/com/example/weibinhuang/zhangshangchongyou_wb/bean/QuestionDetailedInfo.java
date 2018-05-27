package com.example.weibinhuang.zhangshangchongyou_wb.bean;

/**
 * Created by weibinhuang on 18-5-25.
 */

public class QuestionDetailedInfo {
    private String title;
    private String description;
    private String reward;
    private String disappear_at;
    private String tag;
    private String kind;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    private String photo_urls;
    private String questioner_nickname;
    private String questioner_photo_thumbnail_src;
    private String questioner_gender;
    private String id;

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

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getDisappear_at() {
        return disappear_at;
    }

    public void setDisappear_at(String disappear_at) {
        this.disappear_at = disappear_at;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPhoto_urls() {
        return photo_urls;
    }

    public void setPhoto_urls(String photo_urls) {
        this.photo_urls = photo_urls;
    }

    public String getQuestioner_nickname() {
        return questioner_nickname;
    }

    public void setQuestioner_nickname(String questioner_nickname) {
        this.questioner_nickname = questioner_nickname;
    }

    public String getQuestioner_photo_thumbnail_src() {
        return questioner_photo_thumbnail_src;
    }

    public void setQuestioner_photo_thumbnail_src(String questioner_photo_thumbnail_src) {
        this.questioner_photo_thumbnail_src = questioner_photo_thumbnail_src;
    }

    public String getQuestioner_gender() {
        return questioner_gender;
    }

    public void setQuestioner_gender(String questioner_gender) {
        this.questioner_gender = questioner_gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public class QuestionAnswer {

        private String id;
        private String nickname;
        private String photo_thumbnail_src;
        private String gender;
        private String context;
        private String created_at;
        private String praise_num;
        private String comment_num;
        private String is_adopted;
        private String is_praised;
        private String photo_url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPhoto_thumbnail_src() {
            return photo_thumbnail_src;
        }

        public void setPhoto_thumbnail_src(String photo_thumbnail_src) {
            this.photo_thumbnail_src = photo_thumbnail_src;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getPraise_num() {
            return praise_num;
        }

        public void setPraise_num(String praise_num) {
            this.praise_num = praise_num;
        }

        public String getComment_num() {
            return comment_num;
        }

        public void setComment_num(String comment_num) {
            this.comment_num = comment_num;
        }

        public String getIs_adopted() {
            return is_adopted;
        }

        public void setIs_adopted(String is_adopted) {
            this.is_adopted = is_adopted;
        }

        public String getIs_praised() {
            return is_praised;
        }

        public void setIs_praised(String is_praised) {
            this.is_praised = is_praised;
        }

        public String getPhoto_url() {
            return photo_url;
        }

        public void setPhoto_url(String photo_url) {
            this.photo_url = photo_url;
        }
    }
}
