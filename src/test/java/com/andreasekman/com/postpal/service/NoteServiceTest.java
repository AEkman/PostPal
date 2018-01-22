package com.andreasekman.com.postpal.service;

import com.andreasekman.com.postpal.model.Note;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import java.util.Collection;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class NoteServiceTest {

    @Autowired
    private NoteService noteService;

    @Before
    public void setUp() {
        // Reset cache before each test
        noteService.evictCache();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testFindAll() {

        Collection<Note> list = noteService.findAll();

        Assert.assertNotNull("Failure - expected not null", list);
        Assert.assertEquals("Failure - expected size", 4, list.size());
    }

    @Test
    public void testFindOne() {

        Long id = new Long(1);

        Note entity = noteService.findOne(id);

        Assert.assertNotNull("Failure - expected not null", entity);
        Assert.assertEquals("failure - expected id attribute match", id, entity.getId());
    }

    @Test
    public void testFindOneNotFound() {

        Long id = new Long(999999);

        Note entity = noteService.findOne(id);

        Assert.assertNull("Failure - expected null", entity);
    }

    @Test
    public void testCreate() {

        Note entity = new Note();
        entity.setTitle("JUnit test Note 1");
        entity.setContent("JUnit context text 1");

        Note createdEntity = noteService.create(entity);

        Assert.assertNotNull("Failure - expected not null", createdEntity);
        Assert.assertNotNull("Failure - expected id attribute not null", createdEntity.getId());
        Assert.assertEquals("Failure - expected text attribute match", "JUnit context text 1", createdEntity.getContent());

        Collection<Note> list = noteService.findAll();

        Assert.assertEquals("failure - expected size", 5, list.size());

    }

    @Test
    public void testCreateWithId() {

        Exception exception = null;

        Long id = new Long(999999999);

        Note entity = new Note();
        entity.setId(id);
        entity.setTitle("JUnit test Note 1");
        entity.setContent("JUnit context text 1");

        try{
            noteService.create(entity);
        } catch (EntityExistsException eee) {
            exception = eee;
        }

        Assert.assertNotNull("Failure - expected exception", exception);
        Assert.assertTrue("failure - expected EntityExistException", exception != null);
    }

    @Test
    public void testUpdate() {

        Long id = new Long(1);

        Note entity = noteService.findOne(id);

        Assert.assertNotNull("failure - expected not null", entity);

        String updateText = entity.getContent() + " test";
        entity.setContent(updateText);
        Note updatedEntity = noteService.update(entity);

        Assert.assertNotNull("failure - expected updated entity not null", updatedEntity);
        Assert.assertEquals("failure - expected updated entity id attribute changed", id, updatedEntity.getId());
        Assert.assertEquals("failure - expected updated entity text attribute match", updateText, updatedEntity.getContent());
    }

    @Test
    public void testUpdateNotFound() {
        Exception exception = null;

        Long id = new Long(999999999);

        Note entity = new Note();
        entity.setId(id);
        entity.setTitle("JUnit test Note 1");
        entity.setContent("JUnit context text 1");

        try{
            noteService.update(entity);
        } catch (NoResultException nre) {
            exception = nre;
        }

        Assert.assertNotNull("Failure - expected exception", exception);
        Assert.assertTrue("failure - expected EntityExistException", exception != null);
    }

    //TODO fix testDelete, brakes after delete
    @Test
    public void testDelete() {

        Long id = new Long(1);

        Note entity = noteService.findOne(id);

        Assert.assertNotNull("failure - expected not null", entity);

        noteService.delete(id);

        Collection<Note> list = noteService.findAll();

        Assert.assertEquals("failure - expected size", 4, list.size());

        Note deletedEntity = noteService.findOne(id);

        Assert.assertNull("failure - expected entity to be deleted", deletedEntity);
    }

    //TODO add test for findbycontent
    @Test
    public void findByContent() {
    }

}