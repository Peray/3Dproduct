package com.eastdawn.common;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

@SuppressWarnings("serial")
public class BaseSupportTag extends BodyTagSupport {

	protected Object sessionBeanRemote;
	protected java.util.Locale locale = null;

    public void release() {
        super.release();
    }


    ////////////////////////////////////////////////////////////////
    ///                                                          ///
    ///   User methods.                                          ///
    ///                                                          ///
    ///   Modify these methods to customize your tag handler.    ///
    ///                                                          ///
    ////////////////////////////////////////////////////////////////






    /**
     * .
     *
     * This method is called when the JSP engine encounters the start tag,
     * after the attributes are processed.
     * Scripting variables (if any) have their values set here.
     * @return EVAL_BODY_INCLUDE if the JSP engine should evaluate the tag body, otherwise return SKIP_BODY.
     * This method is automatically generated. Do not modify this method.
     * Instead, modify the methods that this method calls.
     */
    public int doStartTag() throws JspException, JspException {
        return EVAL_BODY_BUFFERED;
    }

    /**
     * .
     *
     *
     * This method is called after the JSP engine finished processing the tag.
     * @return EVAL_PAGE if the JSP engine should continue evaluating the JSP page, otherwise return SKIP_PAGE.
     * This method is automatically generated. Do not modify this method.
     * Instead, modify the methods that this method calls.
     */
    public int doEndTag() throws JspException, JspException {
        return EVAL_PAGE;
    }

    /**
     * .
     * Fill in this method to process the body content of the tag.
     * You only need to do this if the tag's BodyContent property
     * is set to "JSP" or "tagdependent."
     * If the tag's bodyContent is set to "empty," then this method
     * will not be called.
     */
    public void writeTagBodyContent(JspWriter out, BodyContent bodyContent) throws
            IOException {
    	
        bodyContent.writeOut(out);

        bodyContent.clearBody();
    }
    private NumberFormat getNumberFormat(Locale locale) {
                return NumberFormat.getNumberInstance(locale);
        }
        private Number toNumber(Object obj) {
            if (obj == null) {
                return null;
            }
            if (obj instanceof Number) {
                return (Number) obj;
            }
            try {
                NumberFormat parser = getNumberFormat(Locale.getDefault());
                return parser.parse(String.valueOf(obj));
            } catch (Exception e) {
                return null;
            }
        }

        protected Integer toInteger(Object obj) {
            if (obj == null || obj.equals("")) {
                return 0;
            } else {
                return new Integer(this.toNumber(obj).intValue());
            }
        }
    /**
     * .
     *
     * Handles exception from processing the body content.
     */
    public void handleBodyContentException(Exception ex) throws JspException {
        // Since the doAfterBody method is guarded, place exception handing code here.
        ex.printStackTrace();
        throw new JspException("error in CategoryShowTag: " + ex);
    }

    /**
     * .
     *
     *
     * This method is called after the JSP engine processes the body content of the tag.
     * @return EVAL_BODY_AGAIN if the JSP engine should evaluate the tag body again, otherwise return SKIP_BODY.
     * This method is automatically generated. Do not modify this method.
     * Instead, modify the methods that this method calls.
     */
    public int doAfterBody() throws JspException {
        return EVAL_BODY_AGAIN;
    }

    @SuppressWarnings("unchecked")
	protected StringBuffer parseExclude(StringBuffer bodyStr) {
        if (bodyStr == null) {
            return new StringBuffer("");
        }
        ArrayList startNum = new ArrayList();
        ArrayList endNum = new ArrayList();
        while (bodyStr.lastIndexOf("<!--Exclude_Start-->") > 0) {
            startNum.clear();
            endNum.clear();
            int j = bodyStr.lastIndexOf("<!--Exclude_Start-->");
            int i = 0;
            int k = 0;
            while (i < j) {
                int temp = bodyStr.indexOf("<!--Exclude_Start-->", i);
                startNum.add(new Integer(temp));
                i = temp + 1;
                k++;
            }
            j = bodyStr.lastIndexOf("<!--Exclude_End-->");
            i = 0;
            k = 0;
            while (i < j) {
                int temp = bodyStr.indexOf("<!--Exclude_End-->", i);
                endNum.add(new Integer(temp));
                i = temp + 1;
                k++;
            }
            int endSign = 0;
            if (startNum.size() != endNum.size()) {
                return null;
            } else {
                for (int m = 0; m < startNum.size(); m++) {
                    int startNumInt = ((Integer) startNum.get(m)).intValue();
                    int endNumInt = ((Integer) endNum.get(m - endSign)).
                                    intValue();
                    if (m != startNum.size() - 1) {
                        if (startNumInt < endNumInt &&
                            ((Integer) startNum.get(m + 1)).intValue() >
                            endNumInt) {
                            bodyStr = bodyStr.replace(startNumInt,
                                    endNumInt + "<!--Exclude_End-->".length(),
                                    "");
                            break;
                        } else {
                            endSign++;
                            continue;
                        }
                    } else {
                        bodyStr = bodyStr.replace(startNumInt,
                                                  endNumInt +
                                                  "<!--Exclude_End-->".length(),
                                                  "");
                        break;
                    }
                }
            }
        } while (bodyStr.indexOf("<!--Hidden_Comment") != -1) {
            bodyStr.replace(bodyStr.indexOf("<!--Hidden_Comment"),
                            bodyStr.indexOf("<!--Hidden_Comment") +
                            "<!--Hidden_Comment".length(), "");
        } while (bodyStr.indexOf("Hidden_Comment-->") != -1) {
            bodyStr.replace(bodyStr.indexOf("Hidden_Comment-->"),
                            bodyStr.indexOf("Hidden_Comment-->") +
                            "Hidden_Comment-->".length(), "");
        }
        return bodyStr;
    }

      protected StringBuffer replaceTagWithRealInfo(StringBuffer bodyStr,
                                                  String tag, String realInfo) {
        if (bodyStr == null || tag == null) {
            return null;
        }
        if (realInfo == null) {
            realInfo = "";
        } while (bodyStr.indexOf(tag) != -1) {
            bodyStr.replace(bodyStr.indexOf(tag),
                            bodyStr.indexOf(tag) + tag.length(), realInfo);
        }
        return bodyStr;
    }

    /**
     * realInfo must is XXX = XXXX;
     *
     * Copyright (c) 2004
     */
      //连接不同页面
    protected StringBuffer replaceTagHyperlink(StringBuffer bodyStr,
                                               String startTag, String endTag,
                                               String realInfo) {    	
    	
        if (bodyStr == null || startTag == null || endTag == null) {
            return null;
        }
        if (realInfo == null) {
            realInfo = "";
        }
        try {
            while (bodyStr.indexOf(startTag) != -1 &&
                   bodyStr.indexOf(endTag) != -1 &&
                   bodyStr.indexOf(startTag) + startTag.length()<bodyStr.indexOf(endTag)){
            	if(realInfo!=null && realInfo.trim().length()>0 && realInfo.trim().toLowerCase().indexOf("://")>-1){
          	    if(bodyStr.indexOf(startTag) != -1 && bodyStr.indexOf(endTag) != -1 &&
                    bodyStr.indexOf(startTag) + startTag.length() <
                    bodyStr.indexOf(endTag)) {
                    String hreF = "(H|h)(R|r)(E|e)(F|f)( )*=";
                    String urlTemp = bodyStr.substring(bodyStr.indexOf(startTag)+startTag.length(),
                                                       bodyStr.indexOf(endTag,
                                                       bodyStr.indexOf(startTag))).replaceAll(hreF, "href=");
                    if (urlTemp.indexOf("href=") > -1) {
                        String srcStartString = urlTemp.substring(urlTemp.indexOf(
                                "href=") + 5);
                        if (srcStartString != null &&
                            srcStartString.trim().length() > 0) {
                            srcStartString = srcStartString.trim();
                            if (srcStartString.indexOf("\"") == 0) {
                                try {
                                    srcStartString = srcStartString.substring(
                                            srcStartString.indexOf("\"") + 1);
                                    srcStartString = srcStartString.substring(0,
                                            srcStartString.indexOf("\""));
                                } catch (Exception ex) {
                                }
                            } else if (srcStartString.indexOf("'") == 0) {
                                if (srcStartString.indexOf("=") > -1) {
                                    try {
                                        srcStartString = srcStartString.
                                                         substring(0,
                                                srcStartString.indexOf("="));
                                        srcStartString = srcStartString.
                                                         substring(0,
                                                srcStartString.lastIndexOf("'"));
                                    } catch (Exception ex1) {
                                    }
                                } else {
                                    srcStartString = srcStartString.substring(0,
                                            srcStartString.lastIndexOf("'") + 1);
                                }
                            } else {
                                if (srcStartString.indexOf("=") > -1) {
                                    srcStartString = srcStartString.substring(0,
                                            srcStartString.indexOf("="));
                                    String newBackString = srcStartString.substring(
                                            srcStartString.lastIndexOf(
                                                    "."));
                                    String picH = ".(h|H)(T|t)(m|M)(l|L)";
                                    if (Pattern.compile(picH).matcher(newBackString).
                                        find()) {
                                        srcStartString = srcStartString.substring(0,
                                                srcStartString.lastIndexOf(".") + 5);
                                    } else {
                                        srcStartString = srcStartString.substring(0,
                                                srcStartString.lastIndexOf(".") + 4);
                                    }
                                } else {
                                    srcStartString = srcStartString.substring(0,
                                            srcStartString.indexOf(">")).trim();
                                }

                            } 
                        } 
                        if (srcStartString.lastIndexOf("/") != -1) {
                            bodyStr.replace(bodyStr.indexOf(startTag),
                                            bodyStr.indexOf(endTag,
                                    bodyStr.indexOf(startTag)) + endTag.length(),
                                    urlTemp.replaceAll(srcStartString,realInfo));
                         } else {
                            bodyStr.replace(bodyStr.indexOf(startTag),
                                            bodyStr.indexOf(endTag,
                                    bodyStr.indexOf(startTag)) + endTag.length(),urlTemp.replaceAll(srcStartString,realInfo));
                        }
                    }
                }
            	}else{
                    bodyStr.replace(bodyStr.indexOf(startTag),
                                bodyStr.indexOf(endTag) + endTag.length(),
                                bodyStr.substring(bodyStr.indexOf(startTag) +startTag.length(),
                                bodyStr.indexOf(endTag)). replaceFirst(".jsp\"", ".jsp?" + realInfo).replaceFirst(".jsf\"", ".jsf?" + realInfo).replaceFirst(".action\"", ".action?" + realInfo));
            	}
            }
        } catch (Exception ex) {
            return null;
        }

        return bodyStr;
    }

    /**
     *
     * Copyright (c) 2004
     *
     * wangjiwei
     */
    protected StringBuffer replaceTagImage(StringBuffer bodyStr,
                                           String startTag,
                                           String endTag,
                                           String realInfo) {
        if (bodyStr == null || startTag == null || endTag == null) {
            return null;
        }
        if (realInfo == null) {
            realInfo = "";
        }
        //图片
        if (bodyStr.indexOf(startTag) != -1 && bodyStr.indexOf(endTag) != -1 &&
            bodyStr.indexOf(startTag) + startTag.length() <
            bodyStr.indexOf(endTag)) {
            String imgH = "<( )*(i|I)(M|m)(G|g)";
            String srcH = "(S|s)(R|r)(C|c)( )*=";
            String imageTemp = bodyStr.substring(bodyStr.indexOf(startTag) +
                                                 startTag.length(),
                                                 bodyStr.
                                                 indexOf(endTag,
                    bodyStr.indexOf(startTag))).replaceAll(imgH, "<img").
                               replaceAll(srcH, "src=");
            if (imageTemp.indexOf("src=") > -1) {
                String srcStartString = imageTemp.substring(imageTemp.indexOf(
                        "src=") + 4);
                if (srcStartString != null &&
                    srcStartString.trim().length() > 0) {
                    srcStartString = srcStartString.trim();
                    if (srcStartString.indexOf("\"") == 0) {
                        try {
                            srcStartString = srcStartString.substring(
                                    srcStartString.indexOf("\"") + 1);
                            srcStartString = srcStartString.substring(0,
                                    srcStartString.indexOf("\""));
                        } catch (Exception ex) {
                        }
                    } else if (srcStartString.indexOf("'") == 0) {
                        if (srcStartString.indexOf("=") > -1) {
                            try {
                                srcStartString = srcStartString.
                                                 substring(0,
                                        srcStartString.indexOf("="));
                                srcStartString = srcStartString.
                                                 substring(0,
                                        srcStartString.lastIndexOf("'"));
                            } catch (Exception ex1) {
                            }
                        } else {
                            srcStartString = srcStartString.substring(0,
                                    srcStartString.lastIndexOf("'") + 1);
                        }
                    } else {
                        if (srcStartString.indexOf("=") > -1) {
                            srcStartString = srcStartString.substring(0,
                                    srcStartString.indexOf("="));
                            String newBackString = srcStartString.substring(
                                    srcStartString.lastIndexOf(
                                            "."));
                            String picH = ".(j|J)(p|P)(E|e)(G|g)";
                            if (Pattern.compile(picH).matcher(newBackString).
                                find()) {
                                srcStartString = srcStartString.substring(0,
                                        srcStartString.lastIndexOf(".") + 5);
                            } else {
                                srcStartString = srcStartString.substring(0,
                                        srcStartString.lastIndexOf(".") + 4);
                            }
                        } else {
                            srcStartString = srcStartString.substring(0,
                                    srcStartString.indexOf(">")).trim();
                        }

                    } //上面对图片的单引号，双引号，无音号
                } //说明确实存在图片
                if (srcStartString.lastIndexOf("/") != -1) {
                    bodyStr.replace(bodyStr.indexOf(startTag),
                                    bodyStr.indexOf(endTag,
                            bodyStr.indexOf(startTag)) + endTag.length(),
                                    imageTemp.replaceFirst(srcStartString,
                            srcStartString.substring(0,
                            srcStartString.lastIndexOf("/") + 1) + realInfo));

                } else {
                    bodyStr.replace(bodyStr.indexOf(startTag),
                                    bodyStr.indexOf(endTag,
                            bodyStr.indexOf(startTag)) + endTag.length(),
                                    imageTemp.replaceFirst(srcStartString,
                            realInfo));

                }
            } //图片的src
        }
        return bodyStr;
    }

    /**
     *
     * Copyright (c) 2004
     *
     * wangjiwei
     */
    protected StringBuffer replaceTagWithRealInfo(StringBuffer bodyStr,
                                                  String startTag,
                                                  String endTag,
                                                  String realInfo) {
        if (bodyStr == null || startTag == null || endTag == null) {
            return null;
        }
        if (realInfo == null) {
            realInfo = "";
        }

        //图片
        while (bodyStr.indexOf(startTag) != -1 && bodyStr.indexOf(endTag) != -1 &&
               bodyStr.indexOf(startTag) + startTag.length() <
               bodyStr.indexOf(endTag)) {
            bodyStr.replace(bodyStr.indexOf(startTag),
                            bodyStr.indexOf(endTag) + endTag.length(), realInfo);
        }

        return bodyStr;
    }


    /**
     *
     * Copyright (c) 2004
     *
      */
    protected StringBuffer replaceInputValuesWithRealInfo(StringBuffer bodyStr,
            String startTag, String endTag, String realInfo) {
        if (bodyStr == null || startTag == null || endTag == null) {
            return null;
        }
        if (realInfo == null) {
            realInfo = "";
        }
        try {
            while (bodyStr.indexOf(startTag) != -1 &&
                   bodyStr.indexOf(endTag) != -1 &&
                   bodyStr.indexOf(startTag) + startTag.length() <
                   bodyStr.indexOf(endTag)) {
                StringBuffer temp = new StringBuffer(bodyStr.substring(bodyStr.
                        indexOf(startTag) +
                        startTag.length(), bodyStr.indexOf(endTag)));
                if (temp.indexOf("value=") < 0) {
                    break;
                }
                temp.replace(temp.indexOf("\"",
                                          temp.indexOf("value=") +
                                          "value=".length()) + "\"".length(),
                             temp.indexOf("\"",
                                          temp.indexOf("\"",
                        temp.indexOf("value=") + "value=".length()) +
                                          "\"".length()),
                             realInfo);
                bodyStr.replace(bodyStr.indexOf(startTag),
                                bodyStr.indexOf(endTag) + endTag.length(),
                                temp.toString());
            }
        } catch (Exception ex) {
            return null;
        }
        return bodyStr;
    }
	    /**
		   * 根据条件得到一共有多少页（通用于任何单表和一对多的表）
		   *
		   * @param countOfPage 一页多少条记录
		   * @param size 总的记录数
		   * @return
		   */
    protected int getPageCount(int countOfPage, int record) {
	     int count = 0;
	     int _count = record % countOfPage;
	
	     if (_count > 0) {
	         if (record < countOfPage) {
	             count = 1;
	         } else {
	             count = record / countOfPage + 1;
	         }
	     } else {
	         count = record / countOfPage;
	     }
	     return count;
	 }

    protected String formatRedStrongString(String oldStr,String formatStr){
		
		if(oldStr==null){
			return "";
		}
		
		if(formatStr==null || formatStr.trim().length()<=0){
			
			return oldStr;
	    }
		String standName="";
		
		if(formatStr!=null && formatStr.trim().length()>0){
			
			formatStr=formatStr.toLowerCase();
			
			while(oldStr.toLowerCase().indexOf(formatStr)>-1){
				
	       	 int start=oldStr.toLowerCase().indexOf(formatStr); 
	       	 
	       	 String forStr=oldStr.substring(0,start);
	       	
	       	 String oldKey=oldStr.substring(start,start+formatStr.length());
	       	 
	       	 standName=standName+forStr+"<font color=\"red\"><b>"+oldKey+"</b></font>";
	       	 
	       	 oldStr=oldStr.substring(start+formatStr.length());
	       	 
	        }
	        if(oldStr!=null && oldStr.length()>0){
	       	 standName=standName+oldStr;
	        }
	        
	        return standName;
	        
		}else{
			return oldStr;
		}
	}
    
    
    protected Locale getLocale() {
        if (locale == null) {
            locale = new java.util.Locale("zh", "CN");
            String localLanStr=pageContext.getServletConfig().getInitParameter("Language.Single");
            if(localLanStr!=null && localLanStr.indexOf(",")>-1){
            	String[] localLanguage=localLanStr.split(",");
            	 locale = new java.util.Locale(localLanguage[0], localLanguage[1]);
            }
            return locale;
        } else {
            return locale;
        }
    }
	
	//分页
	public String getTurnPage(String theme,String cpage,String total,String url,String defaultPara){
		  StringBuilder str = new StringBuilder(); 
		  Integer cpageInt = Integer.valueOf(cpage);   
		  if ("number".equals(theme)) {  //theme="number"的数字样式 [1 2 3 4 5 6 7 8 9 10 > >>]   
              Integer totalInt = Integer.valueOf(total); 
              //如果只有一页，则无需分页   
              str.append("[ ");   
              if (totalInt == 1) {   
                  str.append("<strong>1</strong> ");   
              } else {   
                  //计算一共分几组   
                  int group = (totalInt - 1) / 10 + 1;   
                  //当前第几组   
                  int cgroup = (cpageInt - 1) / 10 + 1;   
                     
                  if (cgroup > 1) {   
                      //当前不是第一组，要显示“<< <”   
                      //<<：返回前一组第一页   
                      //<：返回前一页   
                      str.append("<a href='");   
                      str.append(url);   
//                      str.append("?cpage=" + ((cgroup - 2) * 10 + 1 ) + "&total=" + total+"&ini="+defaultPara);  
                      str.append("?"+defaultPara+"-p-"+((cgroup - 2) * 10 + 1 )+"-z&pageValue="+((cgroup - 2) * 10 + 1 ));  
                      str.append("'>«</a> " );   
                      str.append("<a href='");   
                      str.append(url);   
//                      str.append("?cpage=" + (cpageInt - 1) + "&total=" + total+"&ini="+defaultPara); 
                      str.append("?"+defaultPara+"-p-"+(cpageInt - 1)+"-z&pageValue="+(cpageInt - 1));
                      str.append("'>‹</a> " );   
                  }   
                  //10个为一组显示   
                  for (int i = (cgroup - 1) * 10 + 1; i <= totalInt && i <= cgroup * 10; i++) {   
                      if (cpageInt == i) { //当前页要加粗显示   
                          str.append("<strong>");     
                      }   
                      str.append("<a href='");   
                      str.append(url);   
//                      str.append("?cpage=" + i + "&total=" + total+"&ini="+defaultPara);
                      str.append("?"+defaultPara+"-p-"+i+"-z&pageValue="+i);  
                      str.append("'>" + i + "</a> ");   
                      if (cpageInt == i) {   
                          str.append("</strong>");   
                      }   
                  }   
                  //如果多于1组并且不是最后一组，显示“> >>”   
                  if (group > 1&& cgroup != group) {   
                      //>>：返回下一组最后一页   
                      //>：返回下一页   
                      str.append("<a href='");   
                      str.append(url);
                      str.append("?"+defaultPara+"-p-"+(cpageInt + 1)+"-z&pageValue="+(cpageInt + 1)); 
//                      str.append("?cpage=" + (cpageInt + 1) + "&total=" + total+"&ini="+defaultPara);   
                      str.append("'>›</a> " );   
                      str.append("<a href='");   
                      str.append(url); 
                      str.append("?"+defaultPara+"-p-"+((cgroup * 10 + 10) > totalInt ? totalInt : (cgroup * 10 + 10))+"-z&pageValue="+((cgroup * 10 + 10) > totalInt ? totalInt : (cgroup * 10 + 10))); 
//                      str.append("?cpage=" + ((cgroup * 10 + 10) > totalInt ? totalInt : (cgroup * 10 + 10)) + "&total=" + total+"&ini="+defaultPara);   
                      str.append("'>»</a> " );   
                  }   
              }   
              str.append("]");   
          } 
		return str.toString();
	}
	
	/*
	 * EJB分页
	 * cpage:当前页值,total:总条数,url:请求路径
	 * 李艳
	 */
//	public String getTurnPageEJB(String theme,String cpage,String total,String url){
//		  StringBuilder str = new StringBuilder(); 
//		  Integer cpageInt = Integer.valueOf(cpage);   
//		  if ("number".equals(theme)) {  
//              Integer totalInt = Integer.valueOf(total); 
//              str.append("[ ");   
//              if (totalInt == 1) {   
//                  str.append("<strong>1</strong> ");   
//              } else {    
//                  int group = (totalInt - 1) / 10 + 1;
//                  int cgroup = (cpageInt - 1) / 10 + 1;   
//                     
//                  if (cgroup > 1) {   
//                      str.append("<h:commandLink action=\""+url+"\">"); 
//                      str.append("<f:param name=\"id\" value=\""+((cgroup - 2) * 10 + 1 )+"\" />");
//                      str.append("«</h:commandLink> " ); 
//                      str.append("<h:commandLink action=\""+url+"\">"); 
//                      str.append("<f:param name=\"id\" value=\""+(cpageInt - 1)+"\" />");
//                      str.append("‹</h:commandLink> " );
//                  }   
//                  for (int i = (cgroup - 1) * 10 + 1; i <= totalInt && i <= cgroup * 10; i++) {   
//                      if (cpageInt == i) { 
//                          str.append("<strong>");     
//                      }  
//                      str.append("<h:commandLink action=\""+url+"\">"); 
//                      str.append("<f:param name=\"id\" value=\""+i+"\" />");
//                      str.append(i+"</h:commandLink> " );   
//                      if (cpageInt == i) {   
//                          str.append("</strong>");   
//                      }   
//                  }   
//                  if (group > 1&& cgroup != group) { 
//                	  str.append("<h:commandLink action=\""+url+"\">"); 
//                      str.append("<f:param name=\"id\" value=\""+(cpageInt + 1)+"\" />");
//                      str.append("›</h:commandLink> " ); 
//                      
//                      str.append("<h:commandLink action=\""+url+"\">"); 
//                      str.append("<f:param name=\"id\" value=\""+(cgroup * 10 + 10)+"\" />");
//                      str.append("»</h:commandLink> " );
//                  }   
//              }   
//              str.append("]");   
//          } 
//		return str.toString();
//	}
	

	
	//EJB分页
	public String getTurnPageEJB(String cpage,String total,String url){
		  StringBuilder str = new StringBuilder(); 
		  Integer cpageInt = Integer.valueOf(cpage);   
		 //theme="number"的数字样式 [1 2 3 4 5 6 7 8 9 10 > >>]   
              Integer totalInt = Integer.valueOf(total); 
              //如果只有一页，则无需分页   
              str.append("[ ");   
              if (totalInt == 1) {   
                  str.append("<strong>1</strong> ");   
              } else {   
                  //计算一共分几组   
                  int group = (totalInt - 1) / 10 + 1;   
                  //当前第几组   
                  int cgroup = (cpageInt - 1) / 10 + 1;   
                     
                  if (cgroup > 1) {   
                      //当前不是第一组，要显示“<< <”   
                      //<<：返回前一组第一页   
                      //<：返回前一页   
                      str.append("<a href='");   
                      str.append(url);   
                      str.append("?cpage=" + ((cgroup - 2) * 10 + 1 ) + "&total=" + total);  
//                      str.append("?"+defaultPara+"-p-"+((cgroup - 2) * 10 + 1 )+"-z&pageValue="+((cgroup - 2) * 10 + 1 ));  
                      str.append("'>«</a> " );   
                      str.append("<a href='");   
                      str.append(url);   
                      str.append("?cpage=" + (cpageInt - 1) + "&total=" + total); 
//                      str.append("?"+defaultPara+"-p-"+(cpageInt - 1)+"-z&pageValue="+(cpageInt - 1));
                      str.append("'>‹</a> " );   
                  }   
                  //10个为一组显示   
                  for (int i = (cgroup - 1) * 10 + 1; i <= totalInt && i <= cgroup * 10; i++) {   
                      if (cpageInt == i) { //当前页要加粗显示   
                          str.append("<strong>");     
                      }   
                      str.append("<a href='");   
                      str.append(url);   
                      str.append("?cpage=" + i + "&total=" + total);
//                      str.append("?"+defaultPara+"-p-"+i+"-z&pageValue="+i);  
                      str.append("'>" + i + "</a> ");   
                      if (cpageInt == i) {   
                          str.append("</strong>");   
                      }   
                  }   
                  //如果多于1组并且不是最后一组，显示“> >>”   
                  if (group > 1&& cgroup != group) {   
                      //>>：返回下一组最后一页   
                      //>：返回下一页   
                      str.append("<a href='");   
                      str.append(url);
//                      str.append("?"+defaultPara+"-p-"+(cpageInt + 1)+"-z&pageValue="+(cpageInt + 1)); 
                      str.append("?cpage=" + (cpageInt + 1) + "&total=" + total);   
                      str.append("'>›</a> " );   
                      str.append("<a href='");   
                      str.append(url); 
//                      str.append("?"+defaultPara+"-p-"+((cgroup * 10 + 10) > totalInt ? totalInt : (cgroup * 10 + 10))+"-z&pageValue="+((cgroup * 10 + 10) > totalInt ? totalInt : (cgroup * 10 + 10))); 
                      str.append("?cpage=" + ((cgroup * 10 + 10) > totalInt ? totalInt : (cgroup * 10 + 10)) + "&total=" + total);   
                      str.append("'>»</a> " );   
                  }   
              }   
              str.append("]");  
		return str.toString();
	}
	
	/**
	 * 清楚标签中间部分
	 * @param bodyStr
	 * @param startTag
	 * @param endTag
	 * @return
	 */
	protected StringBuffer replaceTagMiddle(StringBuffer bodyStr,
            String startTag, String endTag) {
		if (bodyStr == null || startTag == null || endTag == null) {
            return null;
        }
        
        try {
			while (bodyStr.indexOf(startTag) != -1 &&
			        bodyStr.indexOf(endTag) != -1 &&
			        bodyStr.indexOf(startTag) + startTag.length()<bodyStr.indexOf(endTag)){
				
				bodyStr.replace(bodyStr.indexOf(startTag) + startTag.length(), 
						bodyStr.indexOf(endTag), "");
			}
		} catch (RuntimeException e) {
			return null;
		}
		
		return bodyStr;
	}
	
}