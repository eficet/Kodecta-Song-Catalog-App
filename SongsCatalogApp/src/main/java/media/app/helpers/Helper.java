package media.app.helpers;

import org.apache.commons.lang3.StringUtils;

public class Helper {
    public static boolean isNullOrEmpty(SongMappingHelper songMappingHelper){
        //checking if the Song helper has empty or null values
        if(songMappingHelper.get_artistId()==null|| songMappingHelper.get_providerId()==null||StringUtils.isBlank(songMappingHelper.get_songName())
        ||StringUtils.isBlank(songMappingHelper.get_genre())|| songMappingHelper.get_publishingDate()==null){
            return true;
        }
        else return false;
    }
    public static boolean isNullOrEmpty(String arg){
        if(StringUtils.isBlank(arg))
            return true;
        else return false;
    }
}
