/*
 * Copyright (C) 2003-2009 eXo Platform SAS.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see<http://www.gnu.org/licenses/>.
 */
package org.exoplatform.ecm.tutorial;

import java.util.Arrays;
import java.util.List;

import org.exoplatform.ecm.webui.component.explorer.control.listener.UIActionBarActionListener;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.UIComponent;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.ext.filter.UIExtensionFilter;
import org.exoplatform.webui.ext.filter.UIExtensionFilters;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          nicolas.filotto@exoplatform.com
 * 2 juillet 2009  
 */
@ComponentConfig(
     events = {
       @EventConfig(listeners = MyUIAction.MyActionNameActionListener.class)
     }
 )
public class MyUIAction extends UIComponent {

     private static final List<UIExtensionFilter> FILTERS = Arrays.asList(new UIExtensionFilter[] {new MyUIFilter()});


	@UIExtensionFilters
	public List<UIExtensionFilter> getFilters() {
		return FILTERS;
	}


	public static class MyActionNameActionListener extends UIActionBarActionListener<MyUIAction> {
		@Override
		protected void processEvent(Event<MyUIAction> event) throws Exception {
			System.out.println("MyActionName of the component MyUIAction has been triggered!!");
		}		
	}
}
