package de.thaso.mpt.fe.bean.navigation;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.context.FacesContext;
import java.util.Stack;

/**
 * TargetBuilder
 *
 * @author thaler
 * @since 2017-06-09
 */
public class TargetBuilder {

    private static final Logger LOG = LoggerFactory.getLogger(TargetBuilder.class);

    public static final String BACK = "*BACK*";

    private static final String BACKURLSTACK = "backurlstack";

    private MaskEnum page;
    private boolean redirect = false;
    private boolean pushBackUrl = false;

    private TargetBuilder(final MaskEnum page) {
        this.page = page;
    }

    public static TargetBuilder create(final MaskEnum page) {
        return new TargetBuilder(page);
    }

    public TargetBuilder withRedirect() {
        redirect = true;
        return this;
    }

    public TargetBuilder pushTargetUrl() {
        pushBackUrl = true;
        return this;
    }

    public TargetBuilder pushActualUrl() {
        pushActualUrlToStack();
        return this;
    }

    public TargetBuilder pushUrl(final String url) {
        pushUrlToStack(url);
        return this;
    }

    public TargetBuilder clearUrlStack() {
        final Stack<String> backUrlStack = retrieveBackUrlStack();
        backUrlStack.clear();
        return this;
    }

    public String build() {
        final StringBuilder builder = new StringBuilder();
        if(page.equals(MaskEnum.BACK)) {
            builder.append(retrieveBackUrl());
        } else {
            builder.append(page.value());
        }
        if (redirect) {
            builder.append("?faces-redirect=true");
        }
        final String url = builder.toString();
        if (pushBackUrl) {
            pushUrlToStack(url);
        }
        LOG.info("target: {}", url);
        return url;
    }

    private String retrieveBackUrl() {
        final Stack<String> backUrlStack = (Stack<String>)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(BACKURLSTACK);
        if (backUrlStack != null && !backUrlStack.empty()) {
            final String backUrl = backUrlStack.pop();
            if (!StringUtils.isEmpty(backUrl)) {
                return backUrl;
            }
        }
        return null;
    }

    private void pushActualUrlToStack() {
        final Stack<String> backUrlStack = retrieveBackUrlStack();

        final String backUrl = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        backUrlStack.push(backUrl);
        LOG.info("pushed: {}", backUrl);
    }

    private void pushUrlToStack(final String url) {
        Stack<String> backUrlStack = retrieveBackUrlStack();

        backUrlStack.push(url);
        LOG.info("pushed: {}", url);
    }

    private Stack<String> retrieveBackUrlStack() {
        final FacesContext facesContext = FacesContext.getCurrentInstance();
        Stack<String> backUrlStack = (Stack<String>)facesContext.getExternalContext().getSessionMap().get(BACKURLSTACK);
        if (backUrlStack == null) {
            backUrlStack = new Stack<>();
            facesContext.getExternalContext().getSessionMap().put(BACKURLSTACK, backUrlStack);
        }
        return backUrlStack;
    }
}