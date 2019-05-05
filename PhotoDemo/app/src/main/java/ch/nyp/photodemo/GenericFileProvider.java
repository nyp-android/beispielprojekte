package ch.nyp.photodemo;

import android.support.v4.content.FileProvider;

/**
 * Dieser FileProvider wird für Android 7.0 und höher benötigt, damit das Foto abgespeichert
 * werden kann im Filesystem.
 * <br/><br/>
 *
 * History:
 * 26.01.2018	1.0	Joel Holzer		Klasse erstellt
 *
 * @author Joel Holzer
 * @version 1.0
 */
public class GenericFileProvider extends FileProvider {}