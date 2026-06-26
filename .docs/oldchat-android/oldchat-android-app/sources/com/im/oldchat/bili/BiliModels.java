package com.im.oldchat.bili;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class BiliModels {

    public static class BiliCookie {
        public long expires;

        @A.c("http_only")
        public int httpOnly;
        public String name;
        public int secure;
        public String value;
    }

    public static class BiliResponse<T> {
        public int code;
        public T data;
        public String message;
        public int ttl;
    }

    public static class CommentContent {
        public String message;
    }

    public static class CommentData {
        public List<CommentReply> hots;
        public CommentPage page;
        public List<CommentReply> replies;
        public CommentTop top;
        public CommentUpper upper;
    }

    public static class CommentMember {
        public String avatar;
        public String uname;
    }

    public static class CommentPage {
        public int count;
        public int num;
        public int size;
    }

    public static class CommentReply {
        public CommentContent content;
        public long ctime;
        public transient boolean hotComment;
        public int like;
        public transient boolean likedByMe;
        public CommentMember member;
        public long parent;
        public int rcount;
        public List<CommentReply> replies;
        public long root;
        public long rpid;
        public transient boolean showAllReplies;
        public transient boolean topComment;
    }

    public static class CommentResult {
        public int code;
        public CommentData data;
        public String message;
        public int ttl;
    }

    public static class CommentTop {
        public CommentReply reply;
        public CommentReply upper;
    }

    public static class CommentUpper {
        public CommentReply top;
    }

    public static class CookieInfo {
        public List<BiliCookie> cookies;
        public List<String> domains;
    }

    public static class DescButton {
        public String text;
    }

    public static class FavFolder {

        @A.c("fav_state")
        public int favState;
        public long fid;
        public long id;

        @A.c("media_count")
        public int mediaCount;
        public long mid;
        public String title;
    }

    public static class FavFolderData {
        public int count;
        public List<FavFolder> list;
    }

    public static class FavFolderResult {
        public int code;
        public FavFolderData data;
        public String message;
        public int ttl;
    }

    public static class FavMedia {

        @A.c("bv_id")
        public String bvId;
        public String bvid;
        public String cover;
        public int duration;
        public long id;
        public String intro;
        public String title;
        public int type;
        public FavUpper upper;
    }

    public static class FavResourceData {

        @A.c("has_more")
        public boolean hasMore;
        public List<FavMedia> medias;
    }

    public static class FavResourceResult {
        public int code;
        public FavResourceData data;
        public String message;
        public int ttl;
    }

    public static class FavUpper {
        public String face;
        public long mid;
        public String name;
    }

    public static class HistoryCursor {
        public String business;
        public long max;
        public int ps;

        @A.c("view_at")
        public long viewAt;
    }

    public static class HistoryData {
        public HistoryCursor cursor;
        public List<HistoryItem> list;
    }

    public static class HistoryDetail {
        public String business;
        public String bvid;
        public long oid;
    }

    public static class HistoryItem {

        @A.c("author_name")
        public String authorName;
        public String cover;
        public int duration;
        public HistoryDetail history;
        public int progress;
        public String title;

        @A.c("view_at")
        public long viewAt;
    }

    public static class HistoryResult {
        public int code;
        public HistoryData data;
        public String message;
        public int ttl;
    }

    public static class NavData {
        public long mid;

        @A.c("wbi_img")
        public WbiImg wbiImg;
    }

    public static class NavResult {
        public int code;
        public NavData data;
        public String message;
        public int ttl;
    }

    public static class PlayUrlData {
        public List<PlayUrlDurl> durl;
        public int quality;
    }

    public static class PlayUrlDurl {
        public long length;
        public long size;
        public String url;
    }

    public static class PlayUrlResult {
        public int code;
        public PlayUrlData data;
        public String message;
        public int ttl;
    }

    public static class QRAuthCodeResponse {

        @A.c("auth_code")
        public String authCode;
        public String url;
    }

    public static class QRAuthCodeResult {
        public int code;
        public QRAuthCodeResponse data;
        public String message;
        public int ttl;
    }

    public static class QRPollResponse {

        @A.c("access_token")
        public String accessToken;

        @A.c("cookie_info")
        public CookieInfo cookieInfo;

        @A.c("expires_in")
        public long expiresIn;
        public long mid;

        @A.c("refresh_token")
        public String refreshToken;

        @A.c("token_info")
        public TokenInfo tokenInfo;
    }

    public static class QRPollResult {
        public int code;
        public QRPollResponse data;
        public String message;
        public int ttl;
    }

    public static class RecommendArgs {
        public long aid;
        public long mid;

        @A.c("up_id")
        public long upId;

        @A.c("up_name")
        public String upName;
    }

    public static class RecommendData {
        public List<RecommendItem> items;
    }

    public static class RecommendItem {
        public RecommendArgs args;

        @A.c("card_goto")
        public String cardGoto;

        @A.c("card_type")
        public String cardType;
        public String cover;

        @A.c("cover_left_text_2")
        public String danmakuCount;

        @A.c("desc_button")
        public DescButton descButton;

        @A.c("cover_right_text")
        public String duration;
        public String face;

        @A.c("goto")
        public String gotoType;
        public String param;

        @A.c("cover_left_text_1")
        public String playCount;
        public String title;
        public String uri;
    }

    public static class RecommendResult {
        public int code;
        public RecommendData data;
        public String message;
        public int ttl;
    }

    public static class SearchData {

        @A.c(alternate = {"num_pages"}, value = "numPages")
        public int numPages;

        @A.c(alternate = {"num_results"}, value = "numResults")
        public int numResults;
        public int page;

        @A.c(alternate = {"page_size"}, value = "pagesize")
        public int pageSize;

        @A.c("result")
        public List<SearchItem> result;
    }

    public static class SearchItem {
        public long aid;
        public String author;
        public String bvid;

        @A.c("video_review")
        public int danmaku;
        public String duration;
        public String pic;
        public String play;
        public String title;
    }

    public static class SearchResult {
        public int code;
        public SearchData data;
        public String message;
    }

    public static class SimpleResult {
        public int code;
        public String message;
    }

    public static class TokenInfo {

        @A.c("access_token")
        public String accessToken;

        @A.c("expires_in")
        public long expiresIn;
        public long mid;

        @A.c("refresh_token")
        public String refreshToken;
    }

    public static class VideoDetailData {
        public long aid;
        public String bvid;
        public long cid;
        public String desc;
        public long duration;
        public VideoOwner owner;
        public List<VideoPage> pages;
        public String pic;
        public VideoStat stat;
        public String title;
    }

    public static class VideoDetailResult {
        public int code;
        public VideoDetailData data;
        public String message;
        public int ttl;
    }

    public static class VideoOwner {
        public String face;
        public long mid;
        public String name;
    }

    public static class VideoPage {
        public long cid;
        public String part;
    }

    public static class VideoStat {
        public int danmaku;
        public int like;
        public int reply;
        public int view;
    }

    public static class WbiImg {

        @A.c("img_url")
        public String imgUrl;

        @A.c("sub_url")
        public String subUrl;
    }

    private BiliModels() {
    }
}
