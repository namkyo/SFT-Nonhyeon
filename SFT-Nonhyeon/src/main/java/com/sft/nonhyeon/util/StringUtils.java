package com.sft.nonhyeon.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * <pre>
 * 시스템명   : JTSB
 * 업무분류   : 공통
 * 클래스명   : StringUtil
 * 클래스개요 : 문자열에 대한 유틸클래스
 * 수정내역   :
 *   수정자	  수정일자			수정내역
 *  -------	----------- ------------------------
 *   최규현	2020.01.12 	 최초작성
 * <pre>
 * 
 * @version 2020.01.12
 * @author 최규현
 */
public class StringUtils {
	
	private static final Logger log = LoggerFactory.getLogger(StringUtils.class);
	
	public static final String YES = "Y";
	public static final String NO = "N";
	
    private static final char HEX_CHAR_LOWER[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    private static final char HEX_CHAR_UPPER[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    private static final String HEXINDEX = "0123456789abcdef          ABCDEF";
    
    
    /** HTML escape문자 코드 **/
    public static final String ESCAPE_CODE_BLANK = "&nbsp;";
    public static final String ESCAPE_CODE_LT = "&lt;";
    public static final String ESCAPE_CODE_GT = "&gt;";
    public static final String ESCAPE_CODE_QUOT = "&quot;";
    public static final String ESCAPE_CODE_APOS = "&apos;";
    public static final String ESCAPE_CODE_AMP = "&amp;";
    

    /**
     * Private constructor, to prevent construction.
     */
    private StringUtils() {} 

    
    /**
     * 주민번호유효성검증
     * @param rbrno
     * @return
     */
    public static boolean validateRbrno(String rbrno) {
    	
    	String chkRbrno = rbrno.replaceAll("[^0-9]", "");
    	
    	if (chkRbrno.length() == 13) {
    		int[] chkNo = {2,3,4,5,6,7,8,9,2,3,4,5};
    		int tot = 0;
    		for (int i=0; i < 12; i++) {
    			tot += chkNo[i] * Integer.parseInt(chkRbrno.substring(i, (i+1)));
    		}
    		int su = 11 - tot%11;
    		if (su > 10) su = su % 10;
    		
    		return (su == Integer.parseInt(chkRbrno.substring(12)));
    	} else {
    		return false;
    	}
    }
    
    /**
     * <p>휴대폰 번호에 포함된 포맷을 제거한다. 숫자만 남겨서 리턴한다.</p>
     * <pre>
     * StringUtil.cpnoFilter("010-4477-0000") = "01044770000"
     * </pre>
     * @param String 휴대폰번호
     * @return 포맷이 제거된 휴대폰번호
     */
    public static String cpnoFilter(String cpno) {
    	
    	if( isNotEmpty(cpno) ){
    		cpno = cpno.replaceAll("[^0-9]", "");
    	}else{
    		cpno = "";
    	}
        return cpno;
    }
    
    /**
     * <p>휴대폰 번호에 포맷을 추가하여 반환한다.</p>
     * <pre>
     * StringUtil.cpnoFormatAdd("01044770000") = "010-4477-0000"
     * </pre>
     * @param String 휴대폰번호
     * @return 포맷이 추가된 휴대폰번호
     */
    public static String cpnoFormatAdd(String cpno) {
    	
    	if( isNotEmpty(cpno) ){
    		
    		cpno = cpno.trim();
    		
    		int noLength = cpno.length();
    		
    		if(noLength > 9) cpno = cpno.substring(0,3)+"-"+cpno.substring(3,noLength-4)+"-"+cpno.substring(noLength-4);
    		
    	}else{
    		cpno = "";
    	}
        return cpno;
    }
    
    /**
     * <p>날짜에 포맷을 추가하여 반환한다.</p>
     * <pre>
     * StringUtil.dateFormatAdd("19800725") = "1990년 07월 25일"
     * StringUtil.dateFormatAdd("1980725") = "1990년 7월 25일"
     * </pre>
     * @param String 휴대폰번호
     * @return 포맷이 추가된 휴대폰번호
     */
    public static String dateFormatAdd(String date) {
    	
    	if( isNotEmpty(date) ){
    		
    		date = date.trim();
    		
    		int dateLength = date.length();
    		
    		if(dateLength > 6) date = date.substring(0,4)+"년 "+date.substring(4,dateLength-2)+"월 "+date.substring(dateLength-2)+"일";
    		
    	}else{
    		date = "";
    	}
        return date;
    }
    
    /**
     * <p>사업자번호에 포맷을 추가하여 반환한다.</p>
     * <pre>
     * StringUtil.bizNoFormatAdd("2348102973") = "234-81-02973"
     * </pre>
     * @param String 휴대폰번호
     * @return 포맷이 추가된 휴대폰번호
     */
    public static String bizNoFormatAdd(String bizno) {
    	
    	if( isNotEmpty(bizno) ){
    		
    		bizno = bizno.trim();
    		
    		int noLength = bizno.length();
    		
    		if(noLength > 9) bizno = bizno.substring(0,3)+"-"+bizno.substring(3,5)+"-"+bizno.substring(5);
    		
    	}else{
    		bizno = "";
    	}
        return bizno;
    }
    
    /**
     * <p>해당문자가 포함되었는지 여부를 반환한다.</p>
     * <pre>
     * StringUtil.include("123456", "abc", 3) = "123abc"
     * </pre>
     * @param String 문자열, String 포함여부 문자열
     * @return boolean  
     */
    public static boolean include(String source, String searchStr) {
        boolean result = false;
        if(isNotEmpty(source) && isNotEmpty(searchStr)){
        	if(source.indexOf(searchStr) > -1){
        		result = true;
        	}
        }
        return result;
    }
    
    /**
     * <p>문구를 조립한다.</p>
     * <pre>
     * StringUtil.chgCode("[%s1]은(는) 필수 항목입니다.", "이름") = "[이름]은(는) 필수 항목입니다."
     * </pre>
     * @param String 문자열, String[] 조립문자열
     * @return String  
     */
    public static String chgCode(String msgConst, String...msgParams) {
    	String chgPoint = "%s";
    	int chgCnt = 1;
    	for(String s : msgParams){
    		msgConst = msgConst.replace(chgPoint+chgCnt, s);
    		chgCnt++;
    	}
        return msgConst;
    }

    /**
     * <p>문자열을 바이트단위로 잘라 반환한다.</p>
     * @param String 원본문자열, int 바이트사이즈
     * @return String  
     */
    public static String cutStr(String src, int cutSize) {

    	byte[] respMsg = src.getBytes();
    		
    	if(respMsg.length > cutSize){
    		byte[] resultMsg = new byte[cutSize];
    		System.arraycopy(respMsg, 0, resultMsg, 0, cutSize);
    		src = new String(resultMsg);
    	}
        return src;
    }
 	
    /**
     * <p>시작 인덱스부터 원본 문자열을 치환 문자열로 변환한다.</p>
     * 
     * <pre>
     * StringUtil.replace("123453", 4, "3", "a") = "12345a"
     * </pre>
     * 
     * @param str 문자열
     * @param startIndex 치환을 위한 시작 인덱스
     * @param oldString 변경할 문자열
     * @param newString 변경 문자열
     * @return 변경 문자열로 치환한 문자열
     */
    public static String replace(String str, int startIndex, String oldString, String newString) {
        if (null == str) {
            return str;
        }

        int firstOccurenceIndex = 0;
        firstOccurenceIndex = str.indexOf(oldString, startIndex);
        if (firstOccurenceIndex == -1) {
            return str;
        }

        String result = str.substring(0, firstOccurenceIndex);
        result = result.concat(newString);
        result = result.concat(str.substring(firstOccurenceIndex + oldString.length(), str.length()));
        return replace(result, firstOccurenceIndex + newString.length(), oldString, newString);
    }

    /**
     * <p>원본 문자열을 치환 문자열로 변환한다.</p>
     * 
     * <pre>
     * StringUtil.replace("123453", "3", "a") = "12a45a"
     * </pre>
     * 
     * @param str 문자열
     * @param oldString 변경할 문자열
     * @param newString 변경 문자열
     * @return 변경 문자열로 치환한 문자열
     */
    public static String replace(String str, String oldString, String newString) {
        return replace(str, 0, oldString, newString);
    }

    /**
     * <p>원본 문자열의 포함된 특정 문자열 첫번째 한개만 새로운 문자열로 변환한다.</p>
     * 
     * <pre>
     * StringUtil.replaceOnce("123453", "3", "a") = "12a453"
     * </pre>
     * 
     * @param str 문자열
     * @param subject 원본 문자열에 포함된 특정 문자열
     * @param 변환할 문자열 
     * @return sb.toString() 새로운 문자열로 변환된 문자열 / source 특정문자열이 없는 경우 원본 문자열
     */
    public static String replaceOnce(String source, String subject, String newString) {
        StringBuffer rtnStr = new StringBuffer();
        String preStr = "";
        String nextStr = source;
        if (source.indexOf(subject) >= 0) {
            preStr = source.substring(0, source.indexOf(subject));
            nextStr = source.substring(source.indexOf(subject) + subject.length(), source.length());
            rtnStr.append(preStr).append(newString).append(nextStr);
            return rtnStr.toString();
        } else {
            return source;
        }
    }
    
    /**
     * <p><code>str</code> 중 <code>searchStr</code>의 시작(index) 위치를 반환한다.</p>
     *
     * <p>입력값 중 <code>null</code>이 있을 경우 <code>-1</code>을 반환한다.</p>
     *
     * <pre>
     * StringUtil.indexOf(null, *)          = -1
     * StringUtil.indexOf(*, null)          = -1
     * StringUtil.indexOf("", "")           = 0
     * StringUtil.indexOf("aabaabaa", "a")  = 0
     * StringUtil.indexOf("aabaabaa", "b")  = 2
     * StringUtil.indexOf("aabaabaa", "ab") = 1
     * StringUtil.indexOf("aabaabaa", "")   = 0
     * </pre>
     *
     * @param str  검색 문자열
     * @param searchStr  검색 대상문자열
     * @return 검색 문자열 중 검색 대상문자열이 있는 시작 위치 검색대상 문자열이 없거나 null인 경우 -1
     */
    public static int indexOf(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return -1;
        }
        return str.indexOf(searchStr);
    }  
    
    /**
     * <p>지정된 길이로 문자열을 자른 후 '...' 문자열을 후미에 붙인 문자열을 반환한다.</p>
     * 
     * <pre>
     * StringUtil.truncate("123456", 3) = "123..."
     * </pre>
     * 
     * @param str 문자열
     * @param slength 지정길이
     * @return 지정길이로 잘라서 후미에 '...' 문자열을 붙인 문자열  
     */
    public static String truncate(String str, int length) {
        if (str.length() > length) {
            str = str.substring(0, length) + "...";
        }
        return str;
    }

    /**
     * <p>지정된 길이로 문자열을 자른 후 추가 문자열을 후미에 붙인 문자열을 반환한다.</p>
     * 
     * <pre>
     * StringUtil.truncate("123456", "abc", 3) = "123abc"
     * </pre>
     * 
     * @param str 문자열
     * @param attachStr 후미에 추가될 문자열
     * @param slength 지정길이
     * @return 지정길이로 잘라서 후미에 지정 문자열을 붙인 문자열  
     */
    public static String truncate(String source, String attachStr, int slength) {
        String returnVal = null;
        if (source != null) {
            if (source.length() > slength) {
                returnVal = source.substring(0, slength) + attachStr;
            } else
                returnVal = source;
        }
        return returnVal;
    }

    /**
     * <p>문자열을 지정한 길이만큼 잘라서 반환한다.</p>
     * 
     * <pre>
     * StringUtil.cutString("123456", 3) = "123"
     * </pre>
     * 
     * @param str 문자열
     * @param slength 지정길이
     * @return 지정길이의 문자열
     */
    public static String cutString(String source, int slength) {
        String result = null;
        if (source != null) {
            if (source.length() > slength) {
                result = source.substring(0, slength);
            } else
                result = source;
        }
        return result;
    }    
    
    /**
     * <p>문자열을 구분자에 따라 구분한 후 스트링 배열로 변환한다.</p>
     * 
     * <pre>
     * StringUtil.split("123/456/789","/") = {"123", "456", "789"}
     * </pre>
     * 
     * @param str 문자열
     * @param delimiter 문자열 구분 문자열
     * @return 문자열 배열
     */
    public static String[] split(String str, String delimiter) {
        StringTokenizer strtok = new StringTokenizer(str, delimiter);
        String[] result = new String[strtok.countTokens()];

        for (int i = 0; i < result.length; i++) {
            result[i] = strtok.nextToken();
        }
        return result;
    }

    /**
     * <p>문자열을 구분자에 따라 구분한 후 스트링 배열로 변환한다.</p>
     * 
     * <pre>
     * StringUtil.split("123/456/789",'/') = {"123", "456", "789"}
     * </pre>
     * 
     * @param str 문자열
     * @param delimiter 문자열 구분 문자
     * @return 문자열 배열
     */
    public static String[] split(String str, char delimiter) {
        return split(str, String.valueOf(delimiter));
    }

    /**
     * <p>스트링 배열을 구분자를 이용해서 String형으로 변환한다.</p>
     * 
     * <pre>
     * StringUtil.join(new String[]{"aaa","123"},"-") = "aaa-123"
     * StringUtil.join(new String[]{"aaa","123","45"},"--") = "aaa--123--45"
     * </pre>
     * 
     * @param strs 문자열 배열
     * @param delimiter 문자열 결합시 추가되는 구분 문자열
     * @return 결합문자열
     */
    public static String join(String[] strs, String delimiter) {
        StringBuffer sb = new StringBuffer();
        if (strs.length == 0) {
            return "";
        }

        sb.append(strs[0].toString());
        for (int i = 1; i < strs.length; i++) {
            sb.append(delimiter);
            sb.append(strs[i].toString());
        }

        return sb.toString();
    }

    /**
     * <p>스트링 배열을 구분자를 이용해서 String형으로 변환한다.</p>
     * 
     * <pre>
     * StringUtil.join(new String[]{"aaa","123"},'-') = "aaa-123"
     * StringUtil.join(new String[]{"aaa","123","45"},'/') = "aaa/123/45"
     * </pre>
     * 
     * @param strs 문자열 배열
     * @param delimiter 문자열 결합시 추가되는 구분 문자
     * @return 결합문자열
     */
    public static String join(String[] strs, char delimiter) {
        return join(strs, String.valueOf(delimiter));
    }

    /**
     * <p>str이 null 이거나 ""일 경우 판단한다.</p>
     * 
     * <pre>
     * </pre>
     * 
     * @param str 문자열
     * @return null 이거나 "" 여부
     */
	static public boolean isEmpty(String str) {
    	
    	if ( (str != null) && !("".equals(str.trim())) ) {
            return false;
        }else{
           	return true;	
        }
    }

    /**
     * <p>String str이 비어있지 않은지 판단한다.</p>
     * <p>MyBatis 조건문에서 사용하기 위함.</p>
     * <pre>
     * </pre>
     * 
     * @param String 자료형
     * @return boolean
     */
	static public boolean isNotEmpty(String str) {

    	if ( (str != null) && !("".equals(str.trim())) ) {
    		return true;
        }else{
        	return false;
        }
    }
    
    /**
     * <p>yn이 "Y" 인지 검사한다.</p>
     * 
     * <pre>
     * </pre>
     * 
     * @param yn "Y" 또는 "N"
     * @return true 또는 false
     */
    public static boolean isYes(String yn) {
    	if(yn == null) {
    		return false;
    	}
    	
    	if(yn.equalsIgnoreCase("Y")) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * <p>obj가 Number 타입인지 검사한다.</p>
     * 
     * <pre>
     * </pre>
     * 
     * @param obj 숫자(정수)
     * @return 결과 문자열
     */
    public static boolean isNumber(Object obj) {
        return isNumber(obj, "default", Locale.getDefault());
    }
    
    /**
     * <p>obj가 Number 타입인지 검사한다.</p>
     * 
     * <pre>
     * </pre>
     * 
     * @param obj 숫자(정수)
     * @param format 포맷
     * @return 결과 문자열
     */
    public static boolean isNumber(Object obj, String format) {
        return isNumber(obj, format, Locale.getDefault());
    }

    /**
     * <p>obj가 Number 타입인지 검사한다.</p>
     * 
     * <pre>
     * </pre>
     * 
     * @param obj 숫자(정수)
     * @param format 포맷
     * @param locale 로케일
     * @return 결과 문자열
     */
    public static boolean isNumber(Object obj, String format, Locale locale) {
        if (null == obj) {
            return false;
        }

        if (obj instanceof Number) {
            return true;
        }

        try {
            NumberFormat parser = null;
            if ((format == null) || (format != null && "default".equalsIgnoreCase(format))) {
                parser = new DecimalFormat();
            } else {
                parser = new DecimalFormat(format, new DecimalFormatSymbols(locale));
            }
            return (parser.parse(String.valueOf(obj)) instanceof Number);
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * <p>숫자의 왼쪽에 '0'문자를 추가하여 길이가 2인 결과 문자열을 반환한다.</p>
     * 
     * <pre>
     * StringUtil.lpad(1)   = "01" 
     * StringUtil.lpad(5)   = "05"
     * StringUtil.lpad(123) = "12"    
     * </pre>
     * 
     * @param number 숫자(정수)
     * @param length 결과 문자열의 길이
     * @return 결과 문자열
     */
    static public String lpad(int number) {
        return lpad(number, 2);
    }

    /**
     * <p>숫자의 왼쪽에 '0'문자를 추가하여 지정한 길이만큼의 결과 문자열을 반환한다.</p>
     * 
     * <pre>
     * StringUtil.lpad(12345,8) = "00012345" 
     * StringUtil.lpad(12345,6) = "012345"  
     * </pre>
     * 
     * @param number 숫자(정수)
     * @param length 결과 문자열의 길이
     * @return 결과 문자열
     */
    public static String lpad(int number, int length) {
        return lpad(number, length, '0');
    }
    
    /**
     * <p>숫자의 왼쪽에 '0'문자를 추가하여 지정한 길이만큼의 결과 문자열을 반환한다.</p>
     * 
     * <pre>
     * StringUtil.lpad(12345,8) = "00012345" 
     * StringUtil.lpad(12345,6) = "012345"  
     * </pre>
     * 
     * @param number 숫자
     * @param length 결과 문자열의 길이
     * @return 결과 문자열
     */
    public static String lpad(BigDecimal number, int length) {
        return lpad(number.toString(), length, '0');
    }
    
    /**
     * <p>숫자의 왼쪽에 특정 문자를 추가하여 지정한 길이만큼의 결과 문자열을 반환한다.</p>
     * 
     * <pre>
     * StringUtil.lpad(12345,8,'0') = "00012345" 
     * StringUtil.lpad(12345,8,'a') = "aaa12345"  
     * </pre>
     * 
     * @param number 숫자(정수)
     * @param length 결과 문자열의 길이
     * @param padChar 추가될 문자
     * @return 결과 문자열
     */
    public static String lpad(long number, int length, char padChar) {
        return lpad(String.valueOf(number), length, padChar);
    }

    /**
     * <p>숫자의 왼쪽에 특정 문자를 추가하여 지정한 길이만큼의 결과 문자열을 반환한다.</p>
     * 
     * <pre>
     * StringUtil.lpad(12345,8,'0') = "00012345" 
     * StringUtil.lpad(12345,8,'a') = "aaa12345"  
     * </pre>
     * 
     * @param number 숫자(정수)
     * @param length 결과 문자열의 길이
     * @param padChar 추가될 문자
     * @return 결과 문자열
     */
    public static String lpad(int number, int length, char padChar) {
        return lpad(String.valueOf(number), length, padChar);
    }

    /**
     * <p>문자열의 왼쪽에 공백(' ') 문자를 추가하여 지정한 길이만큼의 결과 문자열을 반환한다.</p>
     * 
     * <pre>
     * StringUtil.lpad("12345",8) = "   12345" 
     * StringUtil.lpad("12345",7) = "  12345"  
     * </pre>
     * 
     * @param input 문자열
     * @param length 결과 문자열의 길이
     * @return 결과 문자열
     */
    public static String lpad(String input, int length) {
        return lpad(input, length, ' ');
    }
    
    /**
     * <p>문자열의 왼쪽에 공백(' ') 문자를 추가하여 지정한 길이만큼의 결과 문자열을 반환한다.</p>
     * 
     * <pre>
     * StringUtil.lpadByByte("한글1", 6, "EUC-KR") = " 한글1" 
     * StringUtil.lpadByByte("한글1", 6, "KSC5601") = " 한글1" 
     * StringUtil.lpadByByte("한글1", 8, "UTF-8") = " 한글1"  
     * </pre>
     * 
     * @param input 문자열
     * @param length 결과 문자열의 바이트 길이
     * @param encoding 인코딩 이름
     * @return 결과 문자열
     * @throws Exception 
     */
    public static String lpadByByte(String input, int byteLength, String encoding) throws Exception {
        return lpadByByte(input, byteLength, ' ', encoding);
    }

    /**
     * <p>문자열의 왼쪽에 특정 문자를 추가하여 지정한 길이만큼의 결과 문자열을 반환한다.</p>
     * 
     * <pre>
     * StringUtil.lpadByByte("한글1",6, '0', "EUC-KR") = "0한글1" 
     * StringUtil.lpadByByte("한글1",7, 'A', "KSC5601") = "AA한글1"  
     * StringUtil.lpadByByte("한글1",8, 'A', "UTF-8") = "A한글1" 
     * </pre>
     * 
     * @param input 문자열
     * @param length 결과 문자열의 길이
     * @param padChar 추가될 문자
     * @param encoding 인코딩 이름
     * @return 결과 문자열
     * @throws Exception 
     */
    public static String lpadByByte(String input, int byteLength, char padChar, String encoding) throws Exception {
        return padByByte(input, byteLength, String.valueOf(padChar), false, encoding);
    }
    
    /**
     * <p>문자열의 왼쪽에 특정 문자를 추가하여 지정한 길이만큼의 결과 문자열을 반환한다.</p>
     * 
     * <pre>
     * StringUtil.lpad("12345",8,'0') = "00012345" 
     * StringUtil.lpad("12345",8,'a') = "aaa12345"  
     * </pre>
     * 
     * @param input 문자열
     * @param length 결과 문자열의 길이
     * @param padChar 추가될 문자
     * @return 결과 문자열
     */
    public static String lpad(String input, int length, char padChar) {
        return pad(input, length, padChar, false);
    }

    /**
     * <p>문자열의 왼쪽에 특정 문자열을 추가하여 지정한 길이만큼의 결과 문자열을 반환한다.</p>
     * 
     * <pre>
     * StringUtil.lpad("12345",8,"0") = "00012345" 
     * StringUtil.lpad("12345",8,"a") = "aaa12345"  
     * </pre>
     * 
     * @param input 문자열
     * @param length 결과 문자열의 길이
     * @param padChar 추가될 문자
     * @return 결과 문자열
     */
    public static String lpad(String input, int length, String padStr) {
        return pad(input, length, padStr, false);
    }

    /**
     * <p>문자열의 오른쪽에 공백(' ') 문자를 추가하여 지정한 길이만큼의 결과 문자열을 반환한다.</p>
     * 
     * <pre>
     * StringUtil.rpad("12345",8) = "12345   " 
     * StringUtil.rpad("12345",7) = "12345  "  
     * </pre>
     * 
     * @param input 문자열
     * @param length 결과 문자열의 길이
     * @return 결과 문자열
     */
    public static String rpad(String input, int length) {
        return rpad(input, length, ' ');
    }

    /**
     * <p>숫자의 오른쪽에 '0'문자를 추가하여 지정한 길이만큼의 결과 문자열을 반환한다.</p>
     * 
     * <pre>
     * StringUtil.rpad(12345,8,'0') = "12345000" 
     * StringUtil.rpad(12345,8,'a') = "12345aaa"  
     * </pre>
     * 
     * @param number 숫자
     * @param length 결과 문자열의 길이
     * @return 결과 문자열
     */
    public static String rpad(BigDecimal number, int length) {
        return rpad(number.toString(), length, '0');
    }
    
    /**
     * <p>숫자의 오른쪽에 특정 문자를 추가하여 지정한 길이만큼의 결과 문자열을 반환한다.</p>
     * 
     * <pre>
     * StringUtil.rpad(12345,8,'0') = "12345000" 
     * StringUtil.rpad(12345,8,'a') = "12345aaa"  
     * </pre>
     * 
     * @param number 숫자(정수)
     * @param length 결과 문자열의 길이
     * @param padChar 추가될 문자
     * @return 결과 문자열
     */
    public static String rpad(int number, int length, char padChar) {
        return rpad(String.valueOf(number), length, padChar);
    }
    
    /**
     * <p>문자열의 오른쪽에 특정 문자를 추가하여 지정한 길이만큼의 결과 문자열을 반환한다.</p>
     * 
     * <pre>
     * StringUtil.rpad("12345",8,'0') = "12345000" 
     * StringUtil.rpad("12345",8,'a') = "12345aaa"  
     * </pre>
     * 
     * @param input 문자열
     * @param length 결과 문자열의 길이
     * @param padChar 추가될 문자
     * @return 결과 문자열
     */
    public static String rpad(String input, int length, char padChar) {
        return pad(input, length, padChar, true);
    }

    /**
     * <p>문자열의 오른쪽에 특정 문자열을 추가하여 지정한 길이만큼의 결과 문자열을 반환한다.</p>
     * 
     * <pre>
     * StringUtil.rpad("12345",8,"0") = "12345000" 
     * StringUtil.rpad("12345",8,"a") = "12345aaa"  
     * </pre>
     * 
     * @param input 문자열
     * @param length 결과 문자열의 길이
     * @param padStr 추가될 문자열
     * @return 결과 문자열
     */
    public static String rpad(String input, int length, String padStr) {
        return pad(input, length, padStr, true);
    }

    /**
     * <p>숫자의 오른쪽에 '0'문자를 추가하여 지정한 길이만큼의 결과 문자열을 반환한다.</p>
     * 
     * <pre>
     * StringUtil.rpad("12345",8) = "12345000" 
     * </pre>
     * 
     * @param number 숫자(정수)
     * @param length 결과 문자열의 길이
     * @return 결과 문자열
     */
    public static String rpad(int number, int length) {
        return rpad(number, length, '0');
    }
    
    /**
     * <p>문자열의 오른쪽에 공백(' ') 문자를 추가하여 지정한 길이만큼의 결과 문자열을 반환한다.</p>
     * 
     * <pre>
     * StringUtil.rpadByByte("한글123", 8, "EUC-KR")  = "한글123 " 
     * StringUtil.rpadByByte("한글123", 9, "KSC5601") = "한글123  "  
     * </pre>
     * 
     * @param input 문자열
     * @param length 결과 문자열의 바이트 길이
     * @param encoding 인코딩 이름
     * @return 결과 문자열
     * @throws Exception 
     */
    public static String rpadByByte(String input, int byteLength, String encoding) throws Exception {
        return rpadByByte(input, byteLength, ' ', encoding);
    }

    /**
     * <p>문자열의 오른쪽에 특정 문자를 추가하여 지정한 길이만큼의 결과 문자열을 반환한다.</p>
     * 
     * <pre>
     * StringUtil.rpadByByte("한글123", 8, '0', "EUC-KR")  = "한글1230" 
     * StringUtil.rpadByByte("한글123", 9, 'A', "KSC5601") = "한글123AA"  
     * </pre>
     * 
     * @param input 문자열
     * @param length 결과 문자열의 길이
     * @param padChar 추가될 문자
     * @param encoding 인코딩 이름
     * @return 결과 문자열
     * @throws Exception 
     */
    public static String rpadByByte(String input, int byteLength, char padChar, String encoding) throws Exception {
        return padByByte(input, byteLength, String.valueOf(padChar), true, encoding);
    }
    
    /**
     * <p>문자열의 왼쪽 혹은 오른쪽에 특정 문자열을 추가하여 지정한 길이만큼의 결과문자열을 반환한다.</p>
     * 
     * <pre>
     * StringUtil.pad("12345",8,'0',false) = "00012345" 
     * StringUtil.pad("12345",8,'a',true)  = "12345aaa"  
     * </pre>
     * 
     * @param input 문자열
     * @param length 결과 문자열의 길이
     * @param padChar 추가될 문자
     * @param rpad   왼쪽에 추가할지 오른쪽에 추가할지 여부(true:오른쪽, false:왼쪽)
     * @return 결과 문자열
     */
    public static String pad(String input, int length, char padChar, boolean rpad) {
        return pad(input, length, String.valueOf(padChar), rpad);
    }

    /**
     * <p>문자열의 왼쪽 혹은 오른쪽에 특정 문자열을 추가하여 지정한 길이만큼의 결과문자열을 반환한다.</p>
     * 
     * <pre>
     * StringUtil.pad("12345",8,"0",false) = "00012345" 
     * StringUtil.pad("12345",8,"a",true)  = "12345aaa"  
     * </pre>
     * 
     * @param input 문자열
     * @param byteLength 결과 문자열의 길이
     * @param padStr 추가될 문자열
     * @param rpad   왼쪽에 추가할지 오른쪽에 추가할지 여부(true:오른쪽, false:왼쪽)
     * @return 결과 문자열
     * @throws Exception 
     */
    public static String padByByte(String input, int byteLength, String padStr, boolean rpad, String encoding) throws Exception {
    	
    	int totalByteLength = getByteLength(input, encoding);
    	 
        // Create a buffer with an initial capacity set
        StringBuffer buf = new StringBuffer();

        // Determine the number of characters to be padded
        int padCount = byteLength - (input != null ? totalByteLength : 0);

        // Start with the input, if we are rpad-ing
        if (rpad && input != null) {
            buf.append(input);
        }

        // Append with the pad characters
        for (int i = 0; i < padCount; i++) {
            buf.append(padStr);
        }

        // End with the input, if we are lpad-ing
        if (!rpad && input != null) {
            buf.append(input);
        }
        
        int appendedByteLength = getByteLength(buf.toString(), encoding);
        
        return appendedByteLength > byteLength ? 
        		substringByByteLength(buf.toString(), 0, byteLength, encoding) 
        		: buf.toString();
    }
    
    /**
     * <p>문자열의 왼쪽 혹은 오른쪽에 특정 문자열을 추가하여 지정한 길이만큼의 결과문자열을 반환한다.</p>
     * 
     * <pre>
     * StringUtil.pad("12345",8,"0",false) = "00012345" 
     * StringUtil.pad("12345",8,"a",true)  = "12345aaa"  
     * </pre>
     * 
     * @param input 문자열
     * @param length 결과 문자열의 길이
     * @param padStr 추가될 문자열
     * @param rpad   왼쪽에 추가할지 오른쪽에 추가할지 여부(true:오른쪽, false:왼쪽)
     * @return 결과 문자열
     */
    public static String pad(String input, int length, String padStr, boolean rpad) {
        // Create a buffer with an initial capacity set
        StringBuffer buf = new StringBuffer(length);

        // Determine the number of characters to be padded
        int padCount = length - (input != null ? input.length() : 0);

        // Start with the input, if we are rpad-ing
        if (rpad && input != null) {
            buf.append(input);
        }

        // Append with the pad characters
        for (int i = 0; i < padCount; i++) {
            buf.append(padStr);
        }

        // End with the input, if we are lpad-ing
        if (!rpad && input != null) {
            buf.append(input);
        }
        return buf.length() > length ? buf.substring(0, length) : buf.toString();
    }

    static public String ltrim(String str) {
    	return str.replaceAll("^\\s+", "");
    }

    static public String rtrim(String str) {
    	return str.replaceAll("\\s+$", "");
    }

    /**
     * <p>바이트를 핵사 문자열로 변환한다.(소문자)</p>
     * 
     * <pre>
     * </pre>
     * 
     * @param b 	바이트
     * @return 핵사 문자열
     */
    static public String byteToHex(byte b) {
        return byteToHex(b, false);
    }

    /**
     * <p>바이트를 핵사 문자열로 변환한다.</p>
     * 
     * <pre>
     * </pre>
     * 
     * @param b 	바이트
     * @param upper 대/소문자 여부
     * @return 핵사 문자열
     */
    static public String byteToHex(byte b, boolean upper) {
        char[] hexchar = upper ? HEX_CHAR_UPPER : HEX_CHAR_LOWER;
        char[] array = { hexchar[(b >> 4) & 0x0f], hexchar[b & 0x0f] };
        return new String(array);
    }

    /**
     * <p>바이트 배열을 핵사 문자열로 변환한다.(소문자)</p>
     * 
     * <pre>
     * </pre>
     * 
     * @param bt 	바이트배열
     * @return 핵사 문자열
     */
    static public String byteToHex(byte bt[]) {
        return byteToHex(bt, false);
    }

    /**
     * <p>바이트 배열을 핵사 문자열로 변환한다.</p>
     * 
     * <pre>
     * </pre>
     * 
     * @param bt 	바이트배열
     * @param upper 대/소문자 여부
     * @return 핵사 문자열
     */
    static public String byteToHex(byte bt[], boolean upper) {
        int len = bt.length;
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < len; i++) {
            sb.append(byteToHex(bt[i], upper));
        }
        return sb.toString();
    }

    /**
     * <p>문자를 핵사 문자열로 변환한다.(소문자)</p>
     * 
     * <pre>
     * </pre>
     * 
     * @param c 	문자
     * @return 핵사 문자열
     */
    static public String charToHex(char c) {
        return charToHex(c, false);
    }

    /**
     * <p>문자를 핵사 문자열로 변환한다.</p>
     * 
     * <pre>
     * </pre>
     * 
     * @param c 	문자
     * @param upper 대/소문자 여부
     * @return 핵사 문자열
     */
    static public String charToHex(char c, boolean upper) {
        byte hi = (byte) (c >>> 8);
        byte lo = (byte) (c & 0xff);

        return byteToHex(hi, upper) + byteToHex(lo, upper);
    }

    /**
     * <p>핵사 문자열을 바이트 배열로 변환한다.</p>
     * 
     * <pre>
     * </pre>
     * 
     * @param str 	핵사 문자열
     * @return 바이트 배열
     */
    static public byte[] hexToByte(String str) {
        int len = str.length() / 2;
        byte data[] = new byte[len];
        int j = 0;

        for (int i = 0; i < len; i++) {
            char chr = str.charAt(j++);
            int charPos, bitWise;

            charPos = HEXINDEX.indexOf(chr);
            bitWise = (charPos & 0xf) << 4;

            chr = str.charAt(j++);
            charPos = HEXINDEX.indexOf(chr);
            bitWise += (charPos & 0xf);

            data[i] = (byte) bitWise;
        }
        return data;
    }

    /**
     * <p>유니코드 문자열을 핵사 문자열로 변환한다.(소문자)</p>
     * 
     * <pre>
     * </pre>
     * 
     * @param str 	유니코드 문자열
     * @return 핵사 문자열(소문자)
     */
    static public String unicodeToHexString(String str) {
        return unicodeToHexString(str, false);
    }

    /**
     * <p>유니코드 문자열을 핵사 문자열로 변환한다.</p>
     * 
     * <pre>
     * </pre>
     * 
     * @param str 	유니코드 문자열
     * @param upper 대/소문자 여부
     * @return 핵사 문자열
     */
    public static String unicodeToHexString(String str, boolean upper) {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bout);

        try {
            out.writeUTF(str);
            out.close();
            bout.close();
        } catch (IOException e) {
            return null;
        }
        return byteToHex(bout.toByteArray(), upper);
    }

    /**
     * <p>핵사 문자열을 유니코드 문자열로 변환한다.</p>
     * 
     * <pre>
     * </pre>
     * 
     * @param str 	핵사 문자열
     * @return 유니코드 문자열
     */
    public static String hexStringToUnicode(String str) {
        byte[] bt = hexToByte(str);
        ByteArrayInputStream bin = new ByteArrayInputStream(bt);
        DataInputStream in = new DataInputStream(bin);

        try {
            return in.readUTF();
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * <p>유니코드 문자열을 아스키 문자열로 변환한다.(소문자)</p>
     * 
     * <pre>
     * </pre>
     * 
     * @param str 	유니코드 문자열
     * @return 아스키 문자열
     */
    public static String unicodeToAscii(String str) {
        return unicodeToAscii(str, false);
    }

    /**
     * <p>유니코드 문자열을 아스키 문자열로 변환한다.(대문자)</p>
     * 
     * <pre>
     * </pre>
     * 
     * @param str 	유니코드 문자열
     * @return 아스키 문자열
     */
    public static String unicodeToAscii(String str, boolean upper) {
        char[] hexchar = upper ? HEX_CHAR_UPPER : HEX_CHAR_LOWER;

        if (str == null || str.equals("")) {
            return str;
        }

        int len = str.length();
        StringBuffer sb = new StringBuffer(len);

        for (int i = 0; i < len; i++) {
            char chr = str.charAt(i);
            if (chr == '\\') {
                if (i < len - 1 && str.charAt(i + 1) == 'u') {
                    sb.append(chr); // encode the \ as unicode, so 'u' is
                                    // ignored
                    sb.append("u005c"); // splited so the source code is not
                                        // changed...
                } else {
                    sb.append(chr);
                }
            } else if ((chr >= 0x0020) && (chr <= 0x007f)) {
                sb.append(chr); // this is 99%
            } else {
                sb.append("\\u");
                sb.append(hexchar[(chr >> 12) & 0xf]);
                sb.append(hexchar[(chr >> 8) & 0xf]);
                sb.append(hexchar[(chr >> 4) & 0xf]);
                sb.append(hexchar[(chr) & 0xf]);
            }
        }
        return sb.toString();
    }

    /**
     * <p>아스키 문자열을 유니코드 문자열로 변환한다.</p>
     * 
     * <pre>
     * </pre>
     * 
     * @param str 	아스키문자열
     * @return 유니코드 문자열
     */
    public static String asciiToUnicode(String str) {
        if (str == null || str.indexOf("\\u") == -1) {
            return str;
        }

        int len = str.length();
        char chra[] = new char[len];
        int j = 0;

        for (int i = 0; i < len; i++) {
            char chr = str.charAt(i);
            if (chr != '\\' || i == len - 1) {
                chra[j++] = chr;
            } else {
                chr = str.charAt(++i);
                if (chr != 'u' || i == len - 1) {
                    chra[j++] = '\\';
                    chra[j++] = chr;
                } else {
                    int k = (HEXINDEX.indexOf(str.charAt(++i)) & 0xf) << 12;
                    k += (HEXINDEX.indexOf(str.charAt(++i)) & 0xf) << 8;
                    k = (HEXINDEX.indexOf(str.charAt(++i)) & 0xf) << 4;
                    k += (HEXINDEX.indexOf(str.charAt(++i)) & 0xf);
                    chra[j++] = (char) k;
                }
            }
        }
        return new String(chra, 0, j);
    }

    /**
     * <p>InputStream객체를 문자열로 변환한다.</p>
     * 
     * <pre>
     * StringUtil.InputStreamToString(InputStream) = "..."
     * </pre>
     * 
     * @param is 	InputStream객체
     * @return 문자열
     */
    public static String InputStreamToString(InputStream is) {
        InputStreamReader in = new InputStreamReader(is);
        StringWriter write = new StringWriter();
        int blocksize = 8 * 1024; // is this a good value?
        char buffer[] = new char[blocksize];

        try {
            while (true) {
                int readsize = in.read(buffer, 0, blocksize);
                if (readsize == -1) {
                    break;
                }
                write.write(buffer, 0, readsize);
            }

            write.close();
            is.close();
        } catch (IOException e) {
            return null;
        }

        return write.toString();
    }

    /**
     * <p>long형 시간간격을 입력받아 0년 0일 00시간 00분 00초 로 반환한다.</p>
     * 
     * <pre>
     * StringUtil.elapsedTimeAsKorean(62345489) = "2년 1일 14시간 11분 29초"
     * </pre>
     * 
     * @param elapsedTime 	long형 시간 간격
     * @return 0년 0일 00시간 00분 00초
     */
    public static String elapsedTimeAsKorean(long elapsedTime) {
        // 31588585258 => 1015년 6개월 28일 15시간 58초

        long[] tsl = { 60, 60, 24, 30, 12, Long.MAX_VALUE };
        String[] tss = { "초", "분", "시간", "일", "개월", "년" };

        StringBuffer sbElapsedTime = new StringBuffer();

        long quotient = elapsedTime; // 몫
        long remainder = 0; // 나머지
        for (int idx = 0; idx < tsl.length; idx++) {
            remainder = quotient % tsl[idx]; // 나머지
            quotient = quotient / tsl[idx]; // 몫
            if (remainder != 0) {
                sbElapsedTime.insert(0, " " + remainder + tss[idx]);
            }
            if (quotient == 0) {
                break;
            }
        }

        if (sbElapsedTime.length() != 0) {
            sbElapsedTime.deleteCharAt(0);
        }

        return sbElapsedTime.toString();
    }

    /**
     * <p>문자를 int로 변환한다. 정수형태가 아닌 문자열인 경우 0을 반환한다.</p>
     * 
     * <pre>
     * StringUtil.toInt("1234") = 1234  
     * StringUtil.toInt("abcd") = 0
     * </pre>
     * 
     * @param number 	정수로 변환하고자하는 문자열
     * @return 정수
     */
    public static int toInt(String number) {
        return toInt(number, 0);
    }

    /**
     * <p>문자를 int로 변환한다.</p>
     * 
     * <pre>
     * StringUtil.toInt("1234","0") = 1234  
     * StringUtil.toInt("abcd","0") = 0
     * </pre>
     * 
     * @param number 	정수로 변환하고자하는 문자열
     * @param dephault  정수가 아닐경우 리턴되는 값
     * @return 정수
     */
    public static int toInt(String number, int dephault) {
        if (number == null) {
            return 0;
        }

        try {
            return Integer.valueOf(number).intValue();
        } catch (Exception ex) {
            return dephault;
        }
    }

    /**
     * <p>데이타 문자열을 구분자를 기준으로 분리후 String[]로 반환한다.</p>
     * 
     * <pre>
     * StringUtil.getTokenArrayFromString("1234-56-78","-") = {"1234","56",78"}  
     * StringUtil.nvl("1234/56/78","/") = {"1234","56",78"}
     * </pre>
     * 
     * @param str 		문자열
     * @param delimiter 구분자
     * @return 문자배열
     */
    public static String[] getTokenArrayFromString(String str, String delimiter) {
        StringTokenizer st = new StringTokenizer(str, delimiter, false);
        int cnt = st.countTokens();

        List<String> list = new ArrayList<String>();

        // 토큰 배열 생성
        for (int i = 0; i < cnt; i++) {
        	list.add(st.nextToken());
        }

        return list.toArray(new String[0]);
    }

    /**
     * <p>str문자열이 null 혹은 "" 일 경우 ""를 반환한다.</p>
     * 
     * <p>그렇지 않은 경우에는 str문자열을 반환한다.</p>
     * 
     * <pre>
     * StringUtil.nvl(null) = "" 
     * StringUtil.nvl("123") = "123" 
     * </pre>
     * 
     * @param str 	문자열
     * @return 문자열
     */
    public static String nvl(String str) {
        return nvl(str, "");
    }

    /**
     * <p>str문자열이 null 혹은 "" 일 경우 value를 반환한다.</p>
     * 
     * <p>그렇지 않은 경우에는 str문자열을 반환한다.</p>
     * 
     * <pre>
     * StringUtil.nvl(null, "") = "" 
     * StringUtil.nvl("", "abc") = "abc"
     * StringUtil.nvl("123", "") = "123" 
     * </pre>
     * 
     * @param str 	문자열
     * @param value str이 null이거나 ""일 경우 대체할 문자
     * @return 문자열
     */
    public static String nvl(String str, String value) {
        if ( str == null || str.equals("") )
            return value;
        else
            return str;
    }

    /**
     * <p>문자열의 길이를 구한다.(한글은 2, 영문이나 숫자는 1로 인식)</p>
     * 
     * <pre>
     * StringUtil.getByteLength("한글") = 4 
     * StringUtil.getMultipleSpace("abc") = 3 
     * </pre>
     * 
     * @param str 	길이를 구하기 위한 문자열
     * @return 문자열의 바이트 수
     */
    public static int getByteLength(String str) {
    	return getByteLength(str, "UTF-8");
    }
    
    public static int getByteLength(String str, String encoding) {
        if (str == null || "".equals(str)) {
            return 0;
        } else {
        	try {
	            byte srcarr[] = str.getBytes(encoding);
	            return srcarr.length;
        	} catch(Exception e) {
        		return str.getBytes().length;
        	}
        }
    }

    /**
     * <p>spaceCount갯수 만큼의 공백을 반환한다.</p>
     * 
     * <pre>
     * StringUtil.getMultipleSpace(4) = "    " 
     * StringUtil.getMultipleSpace(2) = "  " 
     * </pre>
     * 
     * @param spaceCount 공백갯수
     * @return 공백 문자열
     */
    public static String getMultipleSpace(int spaceCount) {
        StringBuffer spaceBuffer = new StringBuffer("");
        if (spaceCount < 0) {
            throw new IllegalArgumentException("승수는 0 보다 크거나 같아야 합니다.");
        }
        for (int i = 1; i <= spaceCount; i++) {
            spaceBuffer.append(" ");
        }
        return spaceBuffer.toString();
    }

    /**
     * <p>전각문자를 반각문자로 변환한다.</p>
     * 
     * @param src 전각 문자열
     * @return 반각 문자열	
     */
    public static String toHalfChar(String src) {
        if (src == null) return null;

        StringBuffer strBuf = new StringBuffer();
        char c = 0;
        int nSrcLength = src.length();

        for (int i = 0; i < nSrcLength; i++) {
            c = src.charAt(i);
            if (c >= 0xff01 && c <= 0xff5e) {
                c -= 0xfee0;
            } else if (c == 0x3000) {
                c = 0x20;
            }

            if (c == 0x002C) {
                // [，]인경우 제거함(insert/update시 오류 발생)
            } else if (c == 0x0027) {
                // [＇]인경우 제거함(insert/update시 오류 발생)
            } else {
                strBuf.append(c);
            }
        }
        return strBuf.toString();
    }

    /**
     * <p>포멧에 따른 형식으로 변환한다.</p>
     * 
     * <pre>
     * StringUtil.numberFormat("1234567.00","#,###") = "1,234,567" 
     * StringUtil.numberFormat("1234567.1234","#,###.000") = "1,234,567.123" 
     * </pre>
     * 
     * @param numberStr 숫자문자열
     * @param format 	출력포맷
     * @return 숫자스트링에 출력포맷을 적용한 문자열
     * 
     */
    public static String numberFormat(String numberStr, String format) {
        if (numberStr != null && numberStr.length() > 0) {
            java.text.DecimalFormat df = new java.text.DecimalFormat(format);
            double num = (new Double(numberStr)).doubleValue();
            StringBuffer strBuff = new StringBuffer();
            df.format(num, strBuff, new java.text.FieldPosition(java.text.NumberFormat.INTEGER_FIELD));
            return strBuff.toString();
        } else {
            return numberStr;
        }
    }
    
    /**
     * <p>{@link String#toLowerCase()}를 이용하여 소문자로 변환한다.</p>
     *
     * <pre>
     * StringUtil.lowerCase(null)  = null
     * StringUtil.lowerCase("")    = ""
     * StringUtil.lowerCase("aBc") = "abc"
     * </pre>
     *
     * @param str 소문자로 변환되어야 할 문자열
     * @return 소문자로 변환된 문자열, null이 입력되면 <code>null</code> 리턴
     */
    public static String lowerCase(String str) {
        if (str == null) {
            return null;
        }
        
        return str.toLowerCase();
    }
    
    /**
     * <p>{@link String#toUpperCase()}를 이용하여 대문자로 변환한다.</p>
     *
     * <pre>
     * StringUtil.upperCase(null)  = null
     * StringUtil.upperCase("")    = ""
     * StringUtil.upperCase("aBc") = "ABC"
     * </pre>
     *
     * @param str 대문자로 변환되어야 할 문자열
     * @return 대문자로 변환된 문자열, null이 입력되면 <code>null</code> 리턴
     */
    public static String upperCase(String str) {
        if (str == null) {
            return null;
        }
        
        return str.toUpperCase();
    }
    
    /**
     * <p>문자열에서 {@link Character#isWhitespace(char)}에 정의된</p>
     * <p>모든 공백문자를 제거한다.</p>
     *
     * <pre>
     * StringUtil.removeWhitespace(null)         = null
     * StringUtil.removeWhitespace("")           = ""
     * StringUtil.removeWhitespace("abc")        = "abc"
     * StringUtil.removeWhitespace("   ab  c  ") = "abc"
     * </pre>
     *
     * @param str  공백문자가 제거도어야 할 문자열
     * @return 공백문자가 제거된 문자열, null이 입력되면 <code>null</code>이 리턴
     */
    public static String removeWhitespace(String str) {
    	if (isEmpty(str)) {
    		return str;
    	}
    	int sz = str.length();
    	char[] chs = new char[sz];
    	int count = 0;
    	for (int i = 0; i < sz; i++) {
    		if (!Character.isWhitespace(str.charAt(i))) {
    			chs[count++] = str.charAt(i);
    		}
    	}
    	if (count == sz) {
    		return str;
    	}

    	return new String(chs, 0, count);
    }

    /**
     * <p>입력된 String의 앞쪽에서 두번째 인자로 전달된 문자(stripChars)를 모두 제거한다.</p>
     *
     * <pre>
     * StringUtil.stripStart(null, *)          = null
     * StringUtil.stripStart("", *)            = ""
     * StringUtil.stripStart("abc", "")        = "abc"
     * StringUtil.stripStart("abc", null)      = "abc"
     * StringUtil.stripStart("  abc", null)    = "abc"
     * StringUtil.stripStart("abc  ", null)    = "abc  "
     * StringUtil.stripStart(" abc ", null)    = "abc "
     * StringUtil.stripStart("yxabc  ", "xyz") = "abc  "
     * </pre>
     *
     * @param str 지정된 문자가 제거되어야 할 문자열
     * @param stripChars 제거대상 문자열
     * @return 지정된 문자가 제거된 문자열, null이 입력되면 <code>null</code> 리턴
     */
    public static String stripStart(String str, String stripChars) {
    	int strLen;
    	if (str == null || (strLen = str.length()) == 0) {
    		return str;
    	}
    	int start = 0;
    	if (stripChars == null) {
    		while ((start != strLen) && Character.isWhitespace(str.charAt(start))) {
    			start++;
    		}
    	} else if (stripChars.length() == 0) {
    		return str;
    	} else {
    		while ((start != strLen) && (stripChars.indexOf(str.charAt(start)) != -1)) {
    			start++;
    		}
    	}

    	return str.substring(start);
    }


    /**
     * <p>입력된 String의 뒤쪽에서 두번째 인자로 전달된 문자(stripChars)를 모두 제거한다.</p>
     *
     * <pre>
     * StringUtil.stripEnd(null, *)          = null
     * StringUtil.stripEnd("", *)            = ""
     * StringUtil.stripEnd("abc", "")        = "abc"
     * StringUtil.stripEnd("abc", null)      = "abc"
     * StringUtil.stripEnd("  abc", null)    = "  abc"
     * StringUtil.stripEnd("abc  ", null)    = "abc"
     * StringUtil.stripEnd(" abc ", null)    = " abc"
     * StringUtil.stripEnd("  abcyx", "xyz") = "  abc"
     * </pre>
     *
     * @param str 지정된 문자가 제거되어야 할 문자열
     * @param stripChars 제거대상 문자열
     * @return 지정된 문자가 제거된 문자열, null이 입력되면 <code>null</code> 리턴
     */
    public static String stripEnd(String str, String stripChars) {
    	int end;
    	if (str == null || (end = str.length()) == 0) {
    		return str;
    	}

    	if (stripChars == null) {
    		while ((end != 0) && Character.isWhitespace(str.charAt(end - 1))) {
    			end--;
    		}
    	} else if (stripChars.length() == 0) {
    		return str;
    	} else {
    		while ((end != 0) && (stripChars.indexOf(str.charAt(end - 1)) != -1)) {
    			end--;
    		}
    	}

    	return str.substring(0, end);
    }

    /**
     * <p>입력된 String의 앞, 뒤에서 두번째 인자로 전달된 문자(stripChars)를 모두 제거한다.</p>
     * 
     * <pre>
     * StringUtil.strip(null, *)          = null
     * StringUtil.strip("", *)            = ""
     * StringUtil.strip("abc", null)      = "abc"
     * StringUtil.strip("  abc", null)    = "abc"
     * StringUtil.strip("abc  ", null)    = "abc"
     * StringUtil.strip(" abc ", null)    = "abc"
     * StringUtil.strip("  abcyx", "xyz") = "  abc"
     * </pre>
     *
     * @param str 지정된 문자가 제거되어야 할 문자열
     * @param stripChars 제거대상 문자열
     * @return 지정된 문자가 제거된 문자열, null이 입력되면 <code>null</code> 리턴
     */
    public static String strip(String str, String stripChars) {
    	if (isEmpty(str)) {
    		return str;
    	}

    	String srcStr = str;
    	srcStr = stripStart(srcStr, stripChars);

    	return stripEnd(srcStr, stripChars);
    }
    
    /**
     * <p>시작문자와 종료문자 사이의 랜덤 문자열을 구하는 기능</p>
     * 
     * <pre>
     * StringUtil.getRandomStr('a', 'z') = "s"
     * StringUtil.getRandomStr('a', 'z') = "b"
     * StringUtil.getRandomStr('a', 'z') = "x"
     * </pre>
     * 
     * @param startChr 첫 문자
     * @param endChr   마지막문자
     * @return 랜덤문자열
     * @see
     */
    public static String getRandomStr(char startChr, char endChr) {

    	int randomInt;
    	String randomStr = null;

    	// 시작문자 및 종료문자를 아스키숫자로 변환한다.
    	int startInt = Integer.valueOf(startChr);
    	int endInt = Integer.valueOf(endChr);

    	// 시작문자열이 종료문자열보가 클경우
    	if (startInt > endInt) {
    		throw new IllegalArgumentException("Start String: " + startChr + " End String: " + endChr);
    	}

    	try {
    		// 랜덤 객체 생성
    		SecureRandom rnd = new SecureRandom();

    		do {
    			// 시작문자 및 종료문자 중에서 랜덤 숫자를 발생시킨다.
    			randomInt = rnd.nextInt(endInt + 1);
    		} while (randomInt < startInt); // 입력받은 문자 'A'(65)보다 작으면 다시 랜덤 숫자 발생.

    		// 랜덤 숫자를 문자로 변환 후 스트링으로 다시 변환
    		randomStr = (char)randomInt + "";
    	} catch (Exception e) {
    		e.printStackTrace();
    	}

    	// 랜덤문자열를 리턴
    	return randomStr;
    }
    
    /**
     * 문자열을 byte길이만큼 잘라서 가져온다.
     * @param str - 원본 문자열
     * @param beginIndex - 시작 인덱스
     * @param byteLength - 자를 byte 길이
     * @return
     * @throws Exception
     */
    public static String substringByByteLength(String str, int beginIndex, int byteLength) throws Exception
    {
    	return substringByByteLength(str, beginIndex, byteLength, null);
    }
    
    /**
     * 문자열을 byte길이만큼 잘라서 가져온다.
     * @param str - 원본 문자열
     * @param beginIndex - 시작 인덱스
     * @param byteLength - 자를 byte 길이
     * @param encoding - byte 길이 기준이 되는 인코딩. 기본은 'UTF-8'
     * @return
     * @throws Exception
     */
    public static String substringByByteLength(String str, int beginIndex, int byteLength, String encoding) throws Exception
    {
    	if(encoding == null) {
    		encoding = "UTF-8";
    	}
    	
   		byte [] bytes = str.getBytes(encoding);
   		int length = byteLength;
   		if(bytes.length < length) {
   			length = bytes.length;
   		}
   		
   		return new String(bytes, beginIndex, length, encoding);
    }
    
    /**
     * 주민번호로 생년월일 출력
     * @param jumin
     * @return birth (생년월일)
     */
    public static String juminToBirth(String jumin){
        String birth = "";
        
        if(jumin != null && jumin.length() == 13 ){
            String index7 = jumin.charAt(6) + "";
            String f_yy = "";
            if("1".equals(index7) || "2".equals(index7)){
                f_yy = "19";
            }else{
                f_yy = "20";
            }
            birth = f_yy + jumin.substring(0, 2) + "년" + jumin.substring(2, 4) + "월" + jumin.substring(4, 6) + "일";
        }
        return birth;
    }

}