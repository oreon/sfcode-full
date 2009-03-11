package com.wcmda.model;

import javax.faces.model.SelectItem;
import java.util.*;
import java.io.*;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.beans.XMLDecoder;
import java.util.zip.*;

public class AutoCompleteDictionary {
    // list of cities.
    private static List dictionary;

    // currently only 10 flag images, zero based index. 
    private static final int FLAG_COUNT = 9;

    /**
     * Comparator utility for sorting city names.
     */
    public static final Comparator LABEL_COMPARATOR = new Comparator() {
        String s1;
        String s2;
        // compare method for city entries.
        public int compare(Object o1, Object o2) {
            s1 = ((SelectItem) o1).getLabel();
            if (o2 instanceof SelectItem) {
                s2 = ((SelectItem) o2).getLabel();
            } else {
                s2 = o2.toString();
            }
            // compare ingnoring case, give the user a more automated feel when typing
            return s1.compareToIgnoreCase(s2);
        }
    };


    /**
     * Default constructor for an AutoComplete Dictionary. The dictionary values
     * are loaded from file during the construction process.
     */
    public AutoCompleteDictionary() {

        // Raw list of xml cities.
        List cityList = null;

        // load the city dictionary from the compressed xml file.

        // get the path the compressed file
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(true);
        String basePath = session.getServletContext().getRealPath("/");
        basePath += "/city.xml.zip";

        // extract the file
        ZipEntry zipEntry;
        ZipFile zipFile;
        try {
            zipFile = new ZipFile(basePath);
            zipEntry = zipFile.getEntry("city.xml");
        }
        catch (Exception e) {
            return;
        }

        // get the xml stream and decode it.
        if (zipFile.size() > 0 && zipEntry != null) {
            try {
                BufferedInputStream dictionaryStream =
                        new BufferedInputStream(zipFile.getInputStream(zipEntry));
                XMLDecoder xDecoder = new XMLDecoder(dictionaryStream);
                // get the city list.
                cityList = (List) xDecoder.readObject();
                dictionaryStream.close();
                zipFile.close();
                xDecoder.close();
            } catch (ArrayIndexOutOfBoundsException e) {
                return;
            } catch (IOException e) {
                return;
            }
        }

        // Finally load the object from the xml file.
        if (cityList != null) {
            dictionary = new ArrayList(cityList.size());
            City tmpCity;
            int flag = FLAG_COUNT;
            for (int i = cityList.size() - 1; i >= 0; i--) {
                tmpCity = (City) cityList.get(i);
                tmpCity.setImage(flag);
                dictionary.add(new SelectItem(tmpCity, tmpCity.getCity()));
                // we only have a few flags to chose from, assign them.
                flag--;
                if (flag == 0) {
                    flag = FLAG_COUNT;
                }
            }
            cityList.clear();
            // finally sort the list
            Collections.sort(dictionary, LABEL_COMPARATOR);
        }

    }

    /**
     * Gets teh dictionary of cities.
     *
     * @return dictionary list in sorted by city name, ascending.
     */
    public List getDictionary() {
        return dictionary;
    }
}
