@XmlJavaTypeAdapters({
        @XmlJavaTypeAdapter(type= LocalDateTime.class,
                value=LocalDateTimeAdapter.class)})
package org.diotraining.calendar.datastore;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import java.time.LocalDateTime;

