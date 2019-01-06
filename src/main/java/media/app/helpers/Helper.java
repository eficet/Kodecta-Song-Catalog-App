package media.app.helpers;

import org.apache.commons.lang3.StringUtils;

public class Helper {

    /**
     * This function is checking if all the instances of SonMappingHelper are not null or empty
     *
     * @param songMappingHelper
     * @return true or false
     */
    public static boolean isNullOrEmpty(SongMappingHelper songMappingHelper) {

        //checking if the Song helper has empty or null values
        if (songMappingHelper.getArtistId() == null || songMappingHelper.getProviderId() == null || StringUtils.isBlank(songMappingHelper.getSongName())
                || StringUtils.isBlank(songMappingHelper.getGenre()) || songMappingHelper.getPublishingDate() == null) {
            return true;
        } else return false;
    }

    /**
     * This function tests if the string is null or empty
     *
     * @param arg
     * @return true or false
     */
    public static boolean isNullOrEmpty(String arg) {
        if (StringUtils.isBlank(arg))
            return true;
        else return false;
    }
}
