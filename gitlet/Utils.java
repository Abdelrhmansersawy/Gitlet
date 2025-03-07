package gitlet;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;

/**
 * Assorted utilities for Gitlet.
 * This class provides several useful utility functions to simplify common tasks.
 *
 * @author P. N. Hilfinger
 */
public class Utils {

    /** The length of a complete SHA-1 UID as a hexadecimal numeral. */
    public static final int UID_LENGTH = 40;

    /* SHA-1 HASH VALUES */

    /**
     * Returns the SHA-1 hash of the concatenation of the given values.
     * The values can be any mixture of byte arrays and Strings.
     *
     * @param vals The values to hash.
     * @return The SHA-1 hash as a hexadecimal string.
     * @throws IllegalArgumentException If the system does not support SHA-1 or if the input type is invalid.
     */
    public static String sha1(Object... vals) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            for (Object val : vals) {
                if (val instanceof byte[]) {
                    md.update((byte[]) val);
                } else if (val instanceof String) {
                    md.update(((String) val).getBytes(StandardCharsets.UTF_8));
                } else {
                    throw new IllegalArgumentException("Invalid type for SHA-1 hashing.");
                }
            }
            Formatter result = new Formatter();
            for (byte b : md.digest()) {
                result.format("%02x", b);
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("System does not support SHA-1.");
        }
    }

    /**
     * Returns the SHA-1 hash of the concatenation of the strings in the given list.
     *
     * @param vals The list of values to hash.
     * @return The SHA-1 hash as a hexadecimal string.
     */
    public static String sha1(List<Object> vals) {
        return sha1(vals.toArray(new Object[0]));
    }

    /* FILE DELETION */

    /**
     * Deletes the specified file if it exists and is not a directory.
     * Throws an IllegalArgumentException unless the parent directory contains a `.gitlet` directory.
     *
     * @param file The file to delete.
     * @return True if the file was deleted, false otherwise.
     * @throws IllegalArgumentException If the file is not in a Gitlet working directory.
     */
    public static boolean restrictedDelete(File file) {
        if (!(new File(file.getParentFile(), ".gitlet")).isDirectory()) {
            throw new IllegalArgumentException("Not a Gitlet working directory.");
        }
        if (!file.isDirectory()) {
            return file.delete();
        }
        return false;
    }

    /**
     * Deletes the file with the specified name if it exists and is not a directory.
     * Throws an IllegalArgumentException unless the parent directory contains a `.gitlet` directory.
     *
     * @param file The name of the file to delete.
     * @return True if the file was deleted, false otherwise.
     * @throws IllegalArgumentException If the file is not in a Gitlet working directory.
     */
    public static boolean restrictedDelete(String file) {
        return restrictedDelete(new File(file));
    }

    /* READING AND WRITING FILE CONTENTS */

    /**
     * Returns the entire contents of the specified file as a byte array.
     * The file must be a normal file (not a directory).
     *
     * @param file The file to read.
     * @return The contents of the file as a byte array.
     * @throws IllegalArgumentException If the file is not a normal file or cannot be read.
     */
    public static byte[] readContents(File file) {
        if (!file.isFile()) {
            throw new IllegalArgumentException("File must be a normal file.");
        }
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to read file contents: " + e.getMessage());
        }
    }

    /**
     * Returns the entire contents of the specified file as a String.
     * The file must be a normal file (not a directory).
     *
     * @param file The file to read.
     * @return The contents of the file as a String.
     * @throws IllegalArgumentException If the file is not a normal file or cannot be read.
     */
    public static String readContentsAsString(File file) {
        return new String(readContents(file), StandardCharsets.UTF_8);
    }

    /**
     * Writes the concatenation of the given contents to the specified file.
     * Each object in the contents can be either a String or a byte array.
     *
     * @param file The file to write to.
     * @param contents The contents to write.
     * @throws IllegalArgumentException If the file is a directory or if writing fails.
     */
    public static void writeContents(File file, Object... contents) {
        try {
            if (file.isDirectory()) {
                throw new IllegalArgumentException("Cannot overwrite a directory.");
            }
            BufferedOutputStream stream = new BufferedOutputStream(Files.newOutputStream(file.toPath()));
            for (Object obj : contents) {
                if (obj instanceof byte[]) {
                    stream.write((byte[]) obj);
                } else {
                    stream.write(((String) obj).getBytes(StandardCharsets.UTF_8));
                }
            }
            stream.close();
        } catch (IOException | ClassCastException e) {
            throw new IllegalArgumentException("Failed to write file contents: " + e.getMessage());
        }
    }

    /**
     * Reads an object of the specified type from the given file.
     *
     * @param file The file to read from.
     * @param expectedClass The expected class of the object.
     * @param <T> The type of the object.
     * @return The deserialized object.
     * @throws IllegalArgumentException If the file cannot be read or the object cannot be deserialized.
     */
    public static <T extends Serializable> T readObject(File file, Class<T> expectedClass) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            T result = expectedClass.cast(in.readObject());
            in.close();
            return result;
        } catch (IOException | ClassCastException | ClassNotFoundException e) {
            throw new IllegalArgumentException("Failed to read object from file: " + e.getMessage());
        }
    }

    /**
     * Writes the specified serializable object to the given file.
     *
     * @param file The file to write to.
     * @param obj The object to serialize and write.
     */
    public static void writeObject(File file, Serializable obj) {
        writeContents(file, serialize(obj));
    }

    /* DIRECTORIES */

    /** A filename filter that only accepts plain files (not directories). */
    private static final FilenameFilter PLAIN_FILES = (dir, name) -> new File(dir, name).isFile();

    /**
     * Returns a list of the names of all plain files in the specified directory, sorted lexicographically.
     *
     * @param dir The directory to list files from.
     * @return A list of filenames, or null if the directory does not exist.
     */
    public static List<String> plainFilenamesIn(File dir) {
        String[] files = dir.list(PLAIN_FILES);
        if (files == null) {
            return null;
        }
        Arrays.sort(files);
        return Arrays.asList(files);
    }

    /**
     * Returns a list of the names of all plain files in the specified directory, sorted lexicographically.
     *
     * @param dir The path to the directory to list files from.
     * @return A list of filenames, or null if the directory does not exist.
     */
    public static List<String> plainFilenamesIn(String dir) {
        return plainFilenamesIn(new File(dir));
    }

    /* OTHER FILE UTILITIES */

    /**
     * Joins the given path components into a single File object.
     *
     * @param first The first path component.
     * @param others The remaining path components.
     * @return The resulting File object.
     */
    public static File join(String first, String... others) {
        return Paths.get(first, others).toFile();
    }

    /**
     * Joins the given path components into a single File object.
     *
     * @param first The first path component.
     * @param others The remaining path components.
     * @return The resulting File object.
     */
    public static File join(File first, String... others) {
        return Paths.get(first.getPath(), others).toFile();
    }

    /* SERIALIZATION UTILITIES */

    /**
     * Serializes the given object into a byte array.
     *
     * @param obj The object to serialize.
     * @return The serialized byte array.
     * @throws IllegalArgumentException If serialization fails.
     */
    public static byte[] serialize(Serializable obj) {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ObjectOutputStream objectStream = new ObjectOutputStream(stream);
            objectStream.writeObject(obj);
            objectStream.close();
            return stream.toByteArray();
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to serialize object: " + e.getMessage());
        }
    }

    /* MESSAGES AND ERROR REPORTING */

    /**
     * Creates a GitletException with a formatted message.
     *
     * @param msg The message format.
     * @param args The arguments for the message format.
     * @return A new GitletException.
     */
    public static GitletException error(String msg, Object... args) {
        return new GitletException(String.format(msg, args));
    }

    /**
     * Prints a formatted message followed by a newline.
     *
     * @param msg The message format.
     * @param args The arguments for the message format.
     */
    public static void message(String msg, Object... args) {
        System.out.printf(msg, args);
        System.out.println();
    }
}