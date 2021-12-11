package org.worldemunah.digiplax.core.service;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.worldemunah.digiplax.api.dao.PlaqueDao;
import org.worldemunah.digiplax.api.dao.ProjectDao;
import org.worldemunah.digiplax.api.dao.ScheduledPlaqueDao;
import org.worldemunah.digiplax.api.model.PlaqueModel;
import org.worldemunah.digiplax.api.model.ScheduledPlaqueModel;
import org.worldemunah.digiplax.api.service.PlaqueService;
import org.worldemunah.digiplax.api.util.SystemEnv;
import org.worldemunah.digiplax.persistence.main.Plaque;
import org.worldemunah.digiplax.persistence.main.Project;
import org.worldemunah.digiplax.persistence.main.ScheduledPlaque;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User: Ariel
 * Date: 4/14/2019
 */
@Service
public class PlaqueServiceImpl implements PlaqueService {

    private static final Logger log = LoggerFactory.getLogger(PlaqueServiceImpl.class);

    @Autowired
    private PlaqueDao plaqueDao;

    @Autowired
    private ScheduledPlaqueDao scheduledPlaqueDao;

    @Autowired
    private ProjectDao projectDao;

    public List<PlaqueModel> importExcelFile(MultipartFile file) {
        List<PlaqueModel> plaques = new ArrayList<>();
        XSSFWorkbook wb;
        try {
            String fileDir = SystemEnv.getOutputDirFilePath();
            final String filePath = FilenameUtils.concat(fileDir, file.getOriginalFilename());
            byte[] bytes = file.getBytes();
            File dir = new File(fileDir);
            if (!dir.exists()) {
                FileUtils.forceMkdir(dir);
            }

            FileOutputStream writer = new FileOutputStream(filePath);
            writer.write(bytes);
            writer.close();

            wb = new XSSFWorkbook(new BufferedInputStream(new FileInputStream(filePath)));
            XSSFSheet sheet = wb.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() != 0) {
                    try {
                        PlaqueModel plaque = new PlaqueModel();
                        long identifier;
                        CellType cellType = row.getCell(1).getCellType();
                        if (cellType == CellType.NUMERIC) {
                            identifier = (long) row.getCell(1).getNumericCellValue();
                        } else {
                            identifier = Long.parseLong(row.getCell(1).getStringCellValue());
                        }
                        plaque.setIdentifier(identifier);
                        plaque.setDonorFirstName(getStringCellValue(row, 2));
                        plaque.setDonorLastName(getStringCellValue(row, 3));
                        plaque.setHonoreeFirstName(getStringCellValue(row, 4));
                        plaque.setHonoreeLastName(getStringCellValue(row, 5));
                        plaque.setPlaqueText(getStringCellValue(row, 7));

                        plaques.add(plaque);
                    } catch (Exception e) {
                        log.error("Error adding plaque from row " + row.getRowNum(), e);
                    }
                }
            }
        } catch (IOException e) {
            log.error("Error reading file " + file.getOriginalFilename(), e);
        }

        return plaques;
    }

    private String getStringCellValue(Row row, int i) {
        if (row.getCell(i) != null) {
            return row.getCell(i).getStringCellValue();
        }

        return null;
    }

    public void createPlaque(PlaqueModel plaqueModel) {
        Long identifier = plaqueModel.getIdentifier();
        if (identifier == null) {
            throw new RuntimeException("Identifier is required");
        }

        Plaque plaque = plaqueDao.findByIdentifier(identifier);
        if (plaque != null) {
            throw new RuntimeException("Plaque with identifier " + identifier + " already exists");
        }

        Long projectId = plaqueModel.getProjectId();
        if (projectId == null) {
            throw new RuntimeException("Project is required");
        }

        Project project = projectDao.findById(projectId);
        if (project == null) {
            throw new RuntimeException("Project does not exist");
        }

        plaque = new Plaque();
        plaque.setIdentifier(identifier);
        plaque.setProject(project);
        plaque.setDonorFirstName(plaqueModel.getDonorFirstName());
        plaque.setDonorLastName(plaqueModel.getDonorLastName());
        plaque.setHonoreeFirstName(plaqueModel.getHonoreeFirstName());
        plaque.setHonoreeLastName(plaqueModel.getHonoreeLastName());
        plaque.setPlaqueText(plaqueModel.getPlaqueText());
        plaque.setPlaqueHtmlText(plaqueModel.getPlaqueText());

        plaqueDao.insertEntity(plaque);
    }

    public void updatePlaque(Long id, PlaqueModel plaqueModel) {
        Plaque plaque = plaqueDao.findById(id);
        if (plaque == null) {
            throw new RuntimeException("Plaque with id " + id + " does not exist");
        }

        Long projectId = plaqueModel.getProjectId();
        if (projectId == null) {
            throw new RuntimeException("Project is required");
        }

        Project project = projectDao.findById(projectId);
        if (project == null) {
            throw new RuntimeException("Project does not exist");
        }
        plaque.setProject(project);

        Long identifier = plaqueModel.getIdentifier();
        if (identifier == null) {
            throw new RuntimeException("Identifier is required");
        }
        plaque.setIdentifier(identifier);

        String donorFirstName = plaqueModel.getDonorFirstName();
        if (donorFirstName != null) {
            plaque.setDonorFirstName(donorFirstName);
        }

        String donorLastName = plaqueModel.getDonorLastName();
        if (donorLastName != null) {
            plaque.setDonorLastName(donorLastName);
        }

        String honoreeFirstName = plaqueModel.getHonoreeFirstName();
        if (honoreeFirstName != null) {
            plaque.setHonoreeFirstName(honoreeFirstName);
        }

        String honoreeLastName = plaqueModel.getHonoreeLastName();
        if (honoreeLastName != null) {
            plaque.setHonoreeLastName(honoreeLastName);
        }

        String plaqueText = plaqueModel.getPlaqueText();
        if (plaqueText != null) {
            plaque.setPlaqueText(plaqueText);
        }

        String plaqueHtmlText = plaqueModel.getPlaqueHtmlText();
        if (plaqueHtmlText != null) {
            plaque.setPlaqueHtmlText(plaqueHtmlText);
        }

        plaque.setDateUpdated(new Date());

        plaqueDao.updateEntity(plaque);
    }

    @Override
    public void deletePlaque(Long id) {
        Plaque plaque = plaqueDao.findById(id);
        if (plaque == null) {
            throw new RuntimeException("Plaque with id " + id + " does not exist");
        }

        List<ScheduledPlaque> scheduledPlaques = scheduledPlaqueDao.listByPlaque(id);
        for (ScheduledPlaque scheduledPlaque : scheduledPlaques) {
            scheduledPlaqueDao.deleteEntity(scheduledPlaque);
        }

        plaqueDao.deleteEntity(plaque);
    }

    public List<PlaqueModel> getPlaques(Long projectId) {
        List<Plaque> plaques = plaqueDao.listByProject(projectId);
        return plaques.stream()
                .map(plaque -> wrapEntity(plaque))
                .collect(Collectors.toList());
    }

    private PlaqueModel wrapEntity(Plaque plaque) {
        return new PlaqueModel(
                plaque.getId(),
                plaque.getIdentifier(),
                plaque.getDonorFirstName(),
                plaque.getDonorLastName(),
                plaque.getHonoreeFirstName(),
                plaque.getHonoreeLastName(),
                plaque.getPlaqueText(),
                plaque.getPlaqueHtmlText(),
                plaque.getProject().getId(),
                plaque.getProject().getName()
        );
    }

    @Override
    public void createOrUpdatePlaque(PlaqueModel plaqueModel) {
        long identifier = plaqueModel.getIdentifier();
        Plaque plaque = plaqueDao.findByIdentifier(identifier);
        if (plaque == null) {
            createPlaque(plaqueModel);
        } else {
            updatePlaque(plaque.getId(), plaqueModel);
        }
    }

    @Override
    public List<ScheduledPlaqueModel> getActiveScheduledPlaques() {
        return scheduledPlaqueDao.listScheduled().stream()
                .map(scheduledPlaque -> {
                    Plaque plaque = scheduledPlaque.getPlaque();
                    PlaqueModel plaqueModel = wrapEntity(plaque);
                    return new ScheduledPlaqueModel(
                            scheduledPlaque.getId(),
                            scheduledPlaque.getDateFrom(),
                            scheduledPlaque.getDateTo(),
                            plaqueModel,
                            scheduledPlaque.getPriority()
                    );
                })
                .collect(Collectors.toList());
    }


    @Override
    public List<ScheduledPlaqueModel> getPastScheduledPlaques() {
        return scheduledPlaqueDao.listPast().stream()
                .map(scheduledPlaque -> {
                    Plaque plaque = scheduledPlaque.getPlaque();
                    PlaqueModel plaqueModel = wrapEntity(plaque);
                    return new ScheduledPlaqueModel(
                            scheduledPlaque.getId(),
                            scheduledPlaque.getDateFrom(),
                            scheduledPlaque.getDateTo(),
                            plaqueModel,
                            scheduledPlaque.getPriority()
                    );
                })
                .collect(Collectors.toList());
    }

    @Override
    public void createScheduledPlaque(ScheduledPlaqueModel scheduledPlaqueModel) {
        PlaqueModel plaqueModel = scheduledPlaqueModel.getPlaque();
        Long identifier = plaqueModel.getIdentifier();
        Plaque plaque = plaqueDao.findByIdentifier(identifier);
        if (plaque == null) {
            throw new RuntimeException("Plaque with identifier " + identifier + " does not exist");
        }

        Date dateFrom = scheduledPlaqueModel.getDateFrom();
        Date dateTo = scheduledPlaqueModel.getDateTo();
        if (dateTo.before(dateFrom)) {
            throw new RuntimeException("Wrong date range");
        }

        ScheduledPlaque scheduledPlaque = new ScheduledPlaque();
        scheduledPlaque.setPlaque(plaque);
        scheduledPlaque.setDateFrom(dateFrom);
        scheduledPlaque.setDateTo(dateTo);
        scheduledPlaque.setPriority(1);

        scheduledPlaqueDao.insertEntity(scheduledPlaque);
    }

    public void updateScheduledPlaque(Long id, ScheduledPlaqueModel scheduledPlaqueModel) {
        ScheduledPlaque scheduledPlaque = scheduledPlaqueDao.findById(id);
        if (scheduledPlaque == null) {
            throw new RuntimeException("Scheduled plaque with id " + id + " does not exist");
        }

        PlaqueModel plaqueModel = scheduledPlaqueModel.getPlaque();
        Long identifier = plaqueModel.getIdentifier();
        Plaque plaque = plaqueDao.findByIdentifier(identifier);
        if (plaque == null) {
            throw new RuntimeException("Plaque with identifier " + identifier + " does not exist");
        }
        scheduledPlaque.setPlaque(plaque);

        Date dateFrom = scheduledPlaqueModel.getDateFrom();
        Date dateTo = scheduledPlaqueModel.getDateTo();
        if (dateFrom != null && dateTo != null) {
            if (dateTo.before(dateFrom)) {
                throw new RuntimeException("Wrong date range");
            }

            scheduledPlaque.setDateFrom(dateFrom);
            scheduledPlaque.setDateTo(dateTo);
        }

        Integer priority = scheduledPlaqueModel.getPriority();
        if (priority != null) {
            scheduledPlaque.setPriority(priority);
        }

        scheduledPlaqueDao.updateEntity(scheduledPlaque);
    }

    @Override
    public void deleteScheduledPlaque(Long id) {
        ScheduledPlaque scheduledPlaque = scheduledPlaqueDao.findById(id);
        if (scheduledPlaque == null) {
            throw new RuntimeException("Scheduled plaque with id " + id + " does not exist");
        }

        scheduledPlaqueDao.deleteEntity(scheduledPlaque);
    }
}