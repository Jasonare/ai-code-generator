package com.ryan.aicodegenerator.core.saver;

import com.ryan.aicodegenerator.ai.model.HtmlCodeResult;
import com.ryan.aicodegenerator.enums.CodeGenTypeEnum;
import com.ryan.aicodegenerator.exception.BizException;
import com.ryan.aicodegenerator.exception.ErrorCode;

import cn.hutool.core.util.StrUtil;

/**
 * HTML代码文件保存器
 *
 * @author Jasonare
 */
public class HtmlCodeFileSaverTemplate extends CodeFileSaverTemplate<HtmlCodeResult> {

    @Override
    protected CodeGenTypeEnum getCodeType() {
        return CodeGenTypeEnum.HTML;
    }

    @Override
    protected void saveFiles(HtmlCodeResult result, String baseDirPath) {
        // 保存 HTML 文件
        writeToFile(baseDirPath, "index.html", result.getHtmlCode());
    }

    @Override
    protected void validateInput(HtmlCodeResult result) {
        super.validateInput(result);
        // HTML 代码不能为空
        if (StrUtil.isBlank(result.getHtmlCode())) {
            throw new BizException(ErrorCode.SYSTEM_ERROR, "HTML代码内容不能为空");
        }
    }
}
