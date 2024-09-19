package com.bali.personal_trainer.components;

import com.bali.personal_trainer.models.Entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.lang.reflect.Field;
import java.util.*;

public class Components
{

    /**
     * Maps the fields of a given object to a Map, excluding specified fields and adding extra key-value pairs.
     * @param object The object whose fields are to be mapped.
     * @param excludeFields Fields to be excluded from the map.
     * @param additionalData Additional data to be included in the map.
     * @param <T> The type of the object.
     * @return A map containing the object's fields and their values, excluding specified fields and including additional data.
     */
    public static <T> Map<String, Object> mapObjectWithoutFields(T object, Map<String, Object> additionalData, String... excludeFields) {
        Set<String> excludeFieldsSet = new HashSet<>(Arrays.asList(excludeFields));
        Map<String, Object> resultMap = new HashMap<>();

        try {
            // Get all fields of the object's class
            Field[] fields = object.getClass().getDeclaredFields();

            for (Field field : fields)
            {
                // Make field accessible if it is private
                field.setAccessible(true);
                String fieldName = field.getName();

                // Skip the fields that are excluded
                if (!excludeFieldsSet.contains(fieldName))
                {
                    Object fieldValue = field.get(object);
                    resultMap.put(fieldName, fieldValue);
                }
            }
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace(); // Handle exception
        }

        // Add additional data to the result map
        if(additionalData !=null)
        {
            resultMap.putAll(additionalData);
        }

        return resultMap;
    }


    /**
     * Complements the mapObjectWithoutFields but for Users.
     * @param user The user to be mapped
     * @param tokenValue TokenValue to be inserted in the Map, nullable
     * @param excludedFields fileds to be excluded, nullable
     * **/
    public static Object userToBeReturned(User user, String tokenValue, String... excludedFields)
    {
        return excludedFields.length != 0
            ?mapObjectWithoutFields(user, tokenValue!=null? Map.of("token",tokenValue) : null,excludedFields)
            :mapObjectWithoutFields(user, tokenValue!=null? Map.of("token",tokenValue) : null,"tokens","password");
    }

    /**Copies Non-Null values from source to target **/
    public static <T> T copyNonNullElements(Object source, T target) {
        // Iterate over all the fields in the source object
        for (Field field : source.getClass().getDeclaredFields())
        {
            try
            {
                field.setAccessible(true); // To access private fields
                Object value = field.get(source);
                // Only copy properties that are not null
                if (value != null)
                {
                    field.set(target, value);
                }
            } catch (IllegalAccessException e) {return null;}
        }
        return target;
    }

    /**
     * Get User ID From Token
     * @return Returns userID
     * **/
    public static int getUserIdFromToken()
    {
        // Extract the authenticated user's ID from the token
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Integer.parseInt(authentication.getName());
    }
}
