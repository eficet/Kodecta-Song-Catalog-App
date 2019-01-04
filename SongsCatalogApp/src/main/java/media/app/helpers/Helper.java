package media.app.helpers;

import org.apache.commons.lang3.StringUtils;

public class Helper {
    public boolean isNullOrEmpty(SongHelper songHelper){
        //checking if the Song helper has empty or null values
        if(songHelper.get_artistId()==null||songHelper.get_providerId()==null||StringUtils.isBlank(songHelper.get_songName())
        ||StringUtils.isBlank(songHelper.get_genre())||songHelper.get_publishingDate()==null){
            return true;
        }
        else return false;
    }
    public boolean isNullOrEmpty(String arg){
        if(StringUtils.isBlank(arg))
            return true;
        else return false;
    }
}
