/*
 * Copyright (c) Severn Trent Systems. All Rights Reserved.
 *
 * This software is the confidential and proprietary information
 * of Severn Trent Systems ("Confidential Information").  You shall
 * not disclose such Confidential Information and shall use it only
 * in accordance with the terms of your license agreement.
 *
 * SEVERN TRENT SYSTEMS MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT
 * THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. SEVERN TRENT SYSTEMS SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY THE LICENSEE AS A RESULT OF USING,
 * MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */

package csv.loaders;

import java.io.File;

import org.springframework.core.io.FileSystemResource;

import play.Play;

public class CSVFileFactory {

    private static final String FILE_UPLOAD_READ_DIRECTORY = Play.application().configuration().getString("upload.read.dir");

    public static FileSystemResource create (File file) {
    	FileSystemResource fileSR = new FileSystemResource(file);
    	return fileSR;
    }
    
    public static FileSystemResource create(String storedFileName) {
        return create(FILE_UPLOAD_READ_DIRECTORY, storedFileName);
    }

    public static FileSystemResource create(String storedFileDirectory, String storedFileName) {
        FileSystemResource file = new FileSystemResource(storedFileDirectory + storedFileName) ;
        return file;
    }

}
