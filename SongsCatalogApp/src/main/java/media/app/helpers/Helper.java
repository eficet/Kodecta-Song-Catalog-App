package media.app.helpers;

import org.apache.commons.lang3.StringUtils;

public class Helper {
    public static boolean isNullOrEmpty(SongMappingHelper songMappingHelper) {

        //checking if the Song helper has empty or null values
        if (songMappingHelper.getArtistId() == null || songMappingHelper.getProviderId() == null || StringUtils.isBlank(songMappingHelper.getSongName())
                || StringUtils.isBlank(songMappingHelper.getGenre()) || songMappingHelper.getPublishingDate() == null) {
            return true;
        } else return false;
    }

    public static boolean isNullOrEmpty(String arg) {
        if (StringUtils.isBlank(arg))
            return true;
        else return false;
    }
}
