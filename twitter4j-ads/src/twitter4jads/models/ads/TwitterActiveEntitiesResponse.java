package twitter4jads.models.ads;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TwitterActiveEntitiesResponse {
    @SerializedName("data")
    List<TwitterActiveEntity> data;

    public List<TwitterActiveEntity> getData() {
        return data;
    }

    public void setData(List<TwitterActiveEntity> data) {
        this.data = data;
    }
}
