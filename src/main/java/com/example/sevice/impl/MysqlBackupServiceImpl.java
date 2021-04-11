package com.example.sevice.impl;

import com.example.sevice.MysqlBackupService;
import com.example.utils.MySqlBackupRestoreUtils;
import org.springframework.stereotype.Service;

/**
 * @author baoguangyu
 * @version 1.0
 * @date 2021/4/11 12:53
 */
@Service
public class MysqlBackupServiceImpl implements MysqlBackupService {

    @Override
    public boolean backup(String host, String userName, String password, String backupFolderPath, String fileName,
                          String database) throws Exception {
        return MySqlBackupRestoreUtils.backup(host, userName, password, backupFolderPath, fileName, database);
    }

    @Override
    public boolean restore(String restoreFilePath, String host, String userName, String password, String database)
            throws Exception {
        return MySqlBackupRestoreUtils.restore(restoreFilePath, host, userName, password, database);
    }


}
