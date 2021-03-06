/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdlm;

/**
 *
 * @author McKillaGorilla
 * @author GenghisKhan
 */
public enum PropertyType {
    WORKSPACE_HEADING_LABEL,
    
    DETAILS_HEADING_LABEL,
    NAME_PROMPT,
    OWNER_PROMPT,

    ITEMS_HEADING_LABEL,
    
    ADD_ICON, 
    ADD_ITEM_TOOLTIP, 
    
    REMOVE_ICON, 
    REMOVE_ITEM_TOOLTIP, 
    
    MOVE_UP_ICON, 
    MOVE_UP_ITEM_TOOLTIP, 
    
    MOVE_DOWN_ICON, 
    MOVE_DOWN_ITEM_TOOLTIP,
    //added some custom enums
    CATEGORY_COLUMN_HEADING,
    DESCRIPTION_COLUMN_HEADING,
    START_DATE_COLUMN_HEADING,
    END_DATE_COLUMN_HEADING,
    COMPLETED_COLUMN_HEADING, 
    ADD_TITLE, 
    YES,
    NO, 
    CANCEL,
    CATEGORY,
    DESCRIPTION, 
    START_DATE,
    END_DATE, 
    COMPLETED, USER_DENIED, NO_WORK, REMOVE_ITEM, EDIT_ITEM, NO_CHANGE, NAME_OWNER,WOAH_THERE, EMPTY_ITEM
}
