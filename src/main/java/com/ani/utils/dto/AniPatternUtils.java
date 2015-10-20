package com.ani.utils.dto;

import com.ani.utils.exception.AniRuleException;

public class AniPatternUtils{

    //PATTERN STRINGS
    // -USER INFO
    public static String EMAIL_PATTERN = "[a-z0-9_-]+@[a-z0-9_-]+\\.[a-z\\.]+";
    public static String SCREENNAME_PATTERN = "[a-zA-Z0-9-_\\.]+";
	public static String MD5_PATTERN = "[0-9a-f]{32}";
    // -APP & API INFO
    public static String APP_NAME_PATTERN = "[^\\p{Punct}]{1,}";
    public static String API_N_PARAM_NAME_PATTERN = "[a-zA-Z0-9]+";
    public static String API_PARAM_VALUE_PATTERN = "[^\\p{Punct}]{1,}";

    public static String URL_PATTERN = "(http:|https:)//[^[A-Za-z0-9\\\\._\\\\?%&+\\\\\\-=/#:]]*";


    public static boolean isMatches(String src, String pattern){
		return src.matches(pattern);
	}
	
	//verify functions
	//email format
	public static void checkEmailFormatLegality(String email) throws AniRuleException {
		if(!AniPatternUtils.isMatches(email.trim(), AniPatternUtils.EMAIL_PATTERN)){
			throw new AniRuleException("EMAIL_FORMAT_ILLEGAL");
		}
	}
	public static void checkMD5FormatLegality(String email) throws AniRuleException {
		if(!AniPatternUtils.isMatches(email.trim(), AniPatternUtils.MD5_PATTERN)){
			throw new AniRuleException("MD5_FORMAT_ILLEGAL");
		}
	}
	
	public static boolean isReqIdFormatLegal(Long reqId){
		return AniPatternUtils.isMatches(String.valueOf(reqId), AniPatternUtils.MD5_PATTERN);
	}
}