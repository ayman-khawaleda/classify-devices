package cc.madis.DeviceAPI.Services;

public abstract class WebServices<T, U> {
    protected String Url;
    protected String appendedUrl = Url;

    public abstract U makeRequest(T value);
    public abstract U makeRequest(T value, String url);

    public void setUrl(String url) {
        this.Url = url;
    }
    public void appendUrl(String subUrl){
        this.appendedUrl = this.Url + subUrl;
    }
}
