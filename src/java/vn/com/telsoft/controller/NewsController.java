/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.telsoft.controller;

import com.faplib.admin.security.User;
import com.faplib.lib.ClientMessage;
import com.faplib.lib.SystemLogger;
import com.faplib.lib.TSFuncTemplate;
import com.faplib.lib.admin.security.Authorizator;
import com.faplib.lib.util.DataUtil;
import com.faplib.lib.util.ResourceBundleUtil;
import com.faplib.util.StringUtil;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.apache.commons.lang3.StringUtils;
import vn.com.telsoft.entity.News;
import vn.com.telsoft.model.NewsModel;

/**
 *
 * @author Chien Do Xo
 */
@ManagedBean(name = "NewsController")
@ViewScoped
public class NewsController extends TSFuncTemplate implements Serializable {

    private List<News> mlistNews;
    private List<News> mlistNewsFilterred;
    private News[] mselectedNews;
    private News mtmpNews;
    private boolean mbDeleteMany = false;

    private SelectItem[] statusOptions;

    public NewsController() {
        try {
            mlistNews = DataUtil.getData(NewsModel.class, "getListAll");

        } catch (Exception ex) {
            SystemLogger.getLogger().error(ex);
        }
    }
    ////////////////////////////////////////////////////////////////////////

    public void doubleClickSelection() throws Exception {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> map = context.getExternalContext().getRequestParameterMap();
        String strCell = map.get("row").trim();

        if (!StringUtils.isEmpty(strCell)) {
            for (News tmpAttr : mlistNews) {
                if (String.valueOf(tmpAttr.getNewsId()).equals(strCell)) {
                    changeStateView(tmpAttr);
                    break;
                }
            }
        }
    }
    ////////////////////////////////////////////////////////////////////////

    @Override
    public void handOK() {
        if (isADD) {
            try {
                //Access database
                DataUtil.getStringData(NewsModel.class, "add", mtmpNews);

                //Access list
                mlistNews.add(0, new News(mtmpNews));

                //Reset form
                changeStateAdd();

                //Message to client
                ClientMessage.logAdd();

            } catch (Exception ex) {
                if (ex.toString().contains("ORA-01461")) {
                    ClientMessage.logErr(ClientMessage.MESSAGE_TYPE.ERR, ResourceBundleUtil.getCTObjectAsString("PP_NEWS", "content_too_large"));

                } else {
                    ClientMessage.logErr(ClientMessage.MESSAGE_TYPE.ERR, ex.toString());
                }
                SystemLogger.getLogger().error(ex);
            }

        } else {
            try {
                //Access database
                DataUtil.performAction(NewsModel.class, "edit", mtmpNews);

                //Message to client
                ClientMessage.logUpdate();

            } catch (Exception ex) {
                if (ex.toString().contains("ORA-01461")) {
                    ClientMessage.logErr(ClientMessage.MESSAGE_TYPE.ERR, ResourceBundleUtil.getCTObjectAsString("PP_NEWS", "content_too_large"));

                } else {
                    ClientMessage.logErr(ClientMessage.MESSAGE_TYPE.ERR, ex.toString());
                }
                SystemLogger.getLogger().error(ex);
            }
        }
    }
    ////////////////////////////////////////////////////////////////////////

    @Override
    public void handDelete() {
        try {
            //Access database
            if (mbDeleteMany) {
                //Get ids
                String strNewsId = "";
                for (News tmp : mselectedNews) {
                    strNewsId += tmp.getNewsId() + ",";
                }

                //Delete
                DataUtil.performAction(NewsModel.class, "delete", StringUtil.removeLastChar(strNewsId));

                //Refresh
                for (int i = 0; i < mlistNews.size(); i++) {
                    for (News tmp : mselectedNews) {
                        if (mlistNews.get(i).getNewsId() == tmp.getNewsId()) {
                            mlistNews.remove(i--);
                        }
                    }
                }

            } else {
                //Delete
                DataUtil.performAction(NewsModel.class, "delete", String.valueOf(mtmpNews.getNewsId()));

                //Refresh
                for (int i = 0; i < mlistNews.size(); i++) {
                    if (mlistNews.get(i).getNewsId() == mtmpNews.getNewsId()) {
                        mlistNews.remove(i);
                        break;
                    }
                }
            }

            //Message to client
            ClientMessage.logDelete();

        } catch (Exception ex) {
            SystemLogger.getLogger().error(ex);
            ClientMessage.logErr(ClientMessage.MESSAGE_TYPE.ERR, ex.toString());
        }
    }
    ////////////////////////////////////////////////////////////////////////

    public String dateToString(Date input) {
        if (input == null) {
            return "";
        }

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return df.format(input);
    }
    ////////////////////////////////////////////////////////////////////////

    @Override
    public void changeStateAdd() {
        super.changeStateAdd();
        mtmpNews = new News();
        mtmpNews.setCreatedDate(new Date());
        mtmpNews.setCreatedId(Long.parseLong(User.getUserLogged().getUserId()));
        mtmpNews.setCreatedName(User.getUserLogged().getUserName());
    }
    ////////////////////////////////////////////////////////////////////////    

    public void changeStateView(News ett) {
        super.changeStateView();
        mtmpNews = ett;
    }
    ////////////////////////////////////////////////////////////////////////

    public void changeStateEdit(News ett) {
        super.changeStateEdit();
        mtmpNews = ett;
        mtmpNews.setModifiedDate(new Date());
        mtmpNews.setModifiedId(Long.parseLong(User.getUserLogged().getUserId()));
        mtmpNews.setModifiedName(User.getUserLogged().getUserName());
    }
    ////////////////////////////////////////////////////////////////////////

    public void changeStateDel(News ett) {
        super.changeStateDel();
        mbDeleteMany = false;
        mtmpNews = new News(ett);
    }
    ////////////////////////////////////////////////////////////////////////  

    public void changeStateDelMany() {
        super.changeStateDel();
        mbDeleteMany = true;
    }
    //////////////////////////////////////////////////////////////////////// 

    public boolean getPermission(String strRight) {
        boolean bReturnValue = false;

        try {
            bReturnValue = Authorizator.checkAuthorizator().contains(strRight);

        } catch (Exception ex) {
            SystemLogger.getLogger().error(ex);
        }

        return bReturnValue;
    }
    ////////////////////////////////////////////////////////////////////////

    //Setters, Gettters
    public List<News> getMlistNews() {
        return mlistNews;
    }

    public List<News> getMlistNewsFilterred() {
        return mlistNewsFilterred;
    }

    public void setMlistNewsFilterred(List<News> mlistNewsFilterred) {
        this.mlistNewsFilterred = mlistNewsFilterred;
    }

    public News[] getMselectedNews() {
        return mselectedNews;
    }

    public void setMselectedNews(News[] mselectedNews) {
        this.mselectedNews = mselectedNews;
    }

    public News getMtmpNews() {
        return mtmpNews;
    }

    public void setMtmpNews(News mtmpNews) {
        this.mtmpNews = mtmpNews;
    }

    public boolean isMbDeleteMany() {
        return mbDeleteMany;
    }

    public void setMbDeleteMany(boolean mbDeleteMany) {
        this.mbDeleteMany = mbDeleteMany;
    }

    public SelectItem[] getStatusOptions() {
        return statusOptions;
    }

    public void setStatusOptions(SelectItem[] statusOptions) {
        this.statusOptions = statusOptions;
    }

}
