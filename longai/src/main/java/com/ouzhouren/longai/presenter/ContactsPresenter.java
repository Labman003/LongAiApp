package com.ouzhouren.longai.presenter;

import android.content.Context;

import com.ouzhouren.longai.model.MessageModelInterface;

/**
 * Created by BenPC on 2015/8/31.
 */
public class ContactsPresenter {
    private ContactsViewInterface contactsViewInterface;
    private MessageModelInterface messageModelInterface;
    private Context ctx;

    public ContactsPresenter(ContactsViewInterface contactsViewInterface, MessageModelInterface messageModelInterface, Context ctx) {
        this.contactsViewInterface = contactsViewInterface;
        this.messageModelInterface = messageModelInterface;
        this.ctx = ctx;
    }
}
