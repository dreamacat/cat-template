package com.cat.constant;

public class LogConstant {
	
	public static final String BASEDAO_AFTERPROPERTIESSET_ERROR= "basedao_afterpropertiesset_error";
	public static final String BASEDAO_FINDRECORDSWITHTOTALBYSQLID_ERRORA= "basedao_findrecordswithtotalbysqlid_errora";
	public static final String BASEDAO_FINDRECORDSWITHTOTALBYSQLID_ERRORB= "basedao_findrecordswithtotalbysqlid_errorb";
	public static final String DAO_CONFIG_NOT_FOUND= "dao_config_not_found";
	public static final String DAO_CONFIG_ERROR= "dao_config_error";
	public static final String DAO_ARGUMENT_NOT_NULLA= "dao_argument_not_nulla";
	public static final String DAO_ARGUMENT_NOT_NULLB= "dao_argument_not_nullb";
	public static final String DAO_ARGUMENT_NOT_NULLC= "dao_argument_not_nullc";

	
	
	
	/**
     * 
     * 特殊字符常量类<br>
     * 
     * @author
     */
    public static class SpecialCharacter {
        public static final String CHAR_UNDERSCORE = "_";
    }
    
    public static enum CachesNames {
    	LogCache("LogCache", "日志缓存");

        CachesNames(String name, String desc) {
            this.name = name;
            this.desc = desc;
        }

        private String name = null;
        private String desc = null;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
